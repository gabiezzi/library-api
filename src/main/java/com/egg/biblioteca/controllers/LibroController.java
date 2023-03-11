/*
// Curso Egg FullStack
 */
package com.egg.biblioteca.controllers;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.exceptions.MiException;
import com.egg.biblioteca.services.AutorService;
import com.egg.biblioteca.services.EditorialService;
import com.egg.biblioteca.services.LibroService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/* * @author Gabiezzi
 */
@Controller
@RequestMapping("libro/")
public class LibroController {

    @Autowired
    private LibroService libroService;
    @Autowired
    private AutorService autorService;
    @Autowired
    private EditorialService editorialService;

    @GetMapping("/registrar")
    public String registrar(ModelMap model) {

        List<Autor> autores = autorService.listarAutores();
        List<Editorial> editoriales = editorialService.listarEditoriales();

        model.addAttribute("autores", autores);

        model.addAttribute("editoriales", editoriales);

        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo, @RequestParam(required = false) Integer ejemplares,
                           @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap model) {

        try {

            libroService.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);

            model.put("exito", "El libro fue cargado exitosamente!");

        } catch (Exception ex) {
            Logger.getLogger(LibroController.class.getName()).log(Level.SEVERE, null, ex);

            List<Autor> autores = autorService.listarAutores();
            List<Editorial> editoriales = editorialService.listarEditoriales();

            model.addAttribute("autores", autores);

            model.addAttribute("editoriales", editoriales);

            model.put("error", ex.getMessage());

            return "libro_form.html";

        }

        return "libro_form.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap model) {

        model.addAttribute("libros", libroService.listarLibros());
        return "libro_list";

    }

    @GetMapping("/editar/{isbn}")
    public String editar(ModelMap model, @PathVariable Long isbn) {

        model.put("libro", libroService.getOne(isbn));

        List<Autor> autores = autorService.listarAutores();
        List<Editorial> editoriales = editorialService.listarEditoriales();

        model.addAttribute("autores", autores);

        model.addAttribute("editoriales", editoriales);

        return "libro_editar";

    }

    @PostMapping("/editar/{isbn}")
    public String modificar(@PathVariable Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial, ModelMap model) {

        try {
            libroService.modificarLibro(isbn, titulo, idAutor, idEditorial, ejemplares);
            return "redirect:../lista";
        } catch (MiException e) {
            model.put("error", e.getMessage());

            List<Autor> autores = autorService.listarAutores();
            List<Editorial> editoriales = editorialService.listarEditoriales();

            model.addAttribute("autores", autores);

            model.addAttribute("editoriales", editoriales);

            return "libro_editar";
        }


    }
}
