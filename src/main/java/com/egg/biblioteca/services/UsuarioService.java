package com.egg.biblioteca.services;

import com.egg.biblioteca.exceptions.MiException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsuarioService {

    void registrar(String nombre, String email, String password, String password2) throws MiException ;

    void validar(String nombre, String email, String password, String password2) throws MiException ;


}
