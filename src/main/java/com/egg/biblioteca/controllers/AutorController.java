/*
// Curso Egg FullStack
 */
package com.egg.biblioteca.controllers;

import com.egg.biblioteca.exceptions.MiException;
import com.egg.biblioteca.services.impl.AutorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/autor") //localhost:8080/autor
public class AutorController {

    @Autowired
    private AutorServiceImpl autorServiceImpl;

    @GetMapping("/registrar")
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap model) { //mismo nombre que el que tiene el parametro de form name=""


        try {
            autorServiceImpl.crearAutor(nombre);

            model.put("exito", "Autor cargado con exito!");

        } catch (MiException ex) {

            model.put("error", ex.getMessage());
            return "autor_form.html";

        }

        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap model) {

        model.addAttribute("autores", autorServiceImpl.listarAutores());

        return "autor_list.html";
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap model, @PathVariable String id) {

        model.put("autor", autorServiceImpl.getOne(id));

        return "autor_editar";

    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable String id, String nombre, ModelMap model) {

        try {
            autorServiceImpl.modificarAutor(nombre, id);

            return "redirect:../lista";
        } catch (MiException e) {
            model.put("error", e.getMessage());
            return "autor_editar";
        }


    }

}
