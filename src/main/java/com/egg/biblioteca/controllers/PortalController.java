/*
// Curso Egg FullStack
 */
package com.egg.biblioteca.controllers;

import com.egg.biblioteca.entities.Usuario;
import com.egg.biblioteca.exceptions.MiException;
import com.egg.biblioteca.services.UsuarioService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* * @author Gabiezzi
 */
@Controller
@RequestMapping("/")
public class PortalController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email , @RequestParam String password , @RequestParam String password2 , ModelMap model){

        try {
            usuarioService.registrar(nombre, email, password, password2);

            model.put("exito", "usuario registrado con exito!");

            return "index.html";

        } catch (MiException e){

        model.put("error",e.getMessage());
        model.put("nombre",nombre);
        model.put("email",email);

        return "registro.html";

        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false)String error, ModelMap model) {

        if (error!=null){
            model.put("error", "Usuario o contraseña invalidos!");
        }


        return "login.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session) {
        
        Usuario logueado = (Usuario) session.getAttribute("usuarioSession");
        
        if (logueado.getRol().toString().equals("ADMIN")) {
            return "redirect:/admin/dashboard";
        }
        
        return "inicio.html";
    }


}
