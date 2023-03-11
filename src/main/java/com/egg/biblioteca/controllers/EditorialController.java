/*
// Curso Egg FullStack
 */

package com.egg.biblioteca.controllers;

import com.egg.biblioteca.exceptions.MiException;
import com.egg.biblioteca.services.impl.EditorialServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/editorial")
public class EditorialController {

    @Autowired
    EditorialServiceImpl editorialServiceImpl;

    @GetMapping("/registrar")
    public String registrar() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap model) {

        try {

            editorialServiceImpl.crearEditorial(nombre);

            model.put("exito", "La editorial se cargo con exito!");

        } catch (Exception ex) {
            model.put("error", ex.getMessage());
            return "editorial_form.html";

        }

        return "editorial_form.html";

    }

    @GetMapping("/lista")
    public String listar(ModelMap model) {
        model.addAttribute("editoriales", editorialServiceImpl.listarEditoriales());

        return "editorial_list";

    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id , ModelMap model){

        model.put("editorial", editorialServiceImpl.getOne(id));

        return "editorial_editar";

    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable String id, String nombre , ModelMap model){
        try {
            editorialServiceImpl.modificarEditorial(nombre, id);
            return "redirect:../lista";

        }catch (MiException e){

            model.put("error", e.getMessage());
            return "editorial_editar";

        }


    }

}
