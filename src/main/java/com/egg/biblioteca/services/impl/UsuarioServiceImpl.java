package com.egg.biblioteca.services.impl;

import com.egg.biblioteca.entities.Usuario;
import com.egg.biblioteca.enumeraciones.Rol;
import com.egg.biblioteca.exceptions.MiException;
import com.egg.biblioteca.repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void registrar(String nombre, String email, String password, String password2) throws MiException {

        validar(nombre, email, password, password2);

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);

        usuario.setEmail(email);

        usuario.setPassword(new BCryptPasswordEncoder().encode(password)); // decodifica el atributo recibido de password

        usuario.setRol(Rol.USER);

        usuarioRepositorio.save(usuario);
    }

    private void validar(String nombre, String email, String password, String password2) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre no puede estar vacio o nulo");
        }
        if (email == null || email.isEmpty()) {
            throw new MiException("El email no puede estar vacio o nulo");
        }
        if (password == null || password.isEmpty() || password.length() <= 5) {
            throw new MiException("La password no puede estar vacia o nula y debe tener mas de 5 digitos");
        }
        if (!password.equals(password2)) {

            throw new MiException("Las contraseñas no coinciden!");
        }


    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.buscarPorEmail(email); /** Instanciamos un objeto del tipo Usuario, haciendo uso del método buscarPorEmail(email) de la
        clase usuarioRepositorio. **/

        if (usuario != null) { // Verificamos que el objeto Usuario no esté nulo

            List<GrantedAuthority> permisos = new ArrayList(); // Si hay usuario existente, creamos una lista de permisos

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString()); // Otorgamos permisos segun el ROL del usuario

            permisos.add(p); // agregamos a la lista de permisos

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); // Utilizamos los atibutos que nos otorca el pedido al servlet

            HttpSession session = attr.getRequest().getSession(true); // asi guardar la información de nuestra Http session

            session.setAttribute("usuarioSession", usuario);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos); // retornamos un  user con su mail , contraseña y permisos

        } else {

            return null;
        }
    }

    
}

