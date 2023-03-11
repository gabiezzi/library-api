/*
// Curso Egg FullStack
 */

package com.egg.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/* * @author Gabiezzi
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping("/dashboard")
    public String panelAdmin(){
        return "panel.html";
    }

}
