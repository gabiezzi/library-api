/*
// Curso Egg FullStack
 */
package com.egg.biblioteca.controllers;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.entities.Libro;
import com.egg.biblioteca.exceptions.MiException;
import com.egg.biblioteca.services.impl.AutorServiceImpl;
import com.egg.biblioteca.services.impl.EditorialServiceImpl;
import com.egg.biblioteca.services.impl.LibroServiceImpl;

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
    private LibroServiceImpl libroServiceImpl;
    @Autowired
    private AutorServiceImpl autorServiceImpl;
    @Autowired
    private EditorialServiceImpl editorialServiceImpl;

    @GetMapping("/registrar")
    public String registrar(ModelMap model) {

        List<Autor> autores = autorServiceImpl.listarAutores();
        List<Editorial> editoriales = editorialServiceImpl.listarEditoriales();

        model.addAttribute("autores", autores);

        model.addAttribute("editoriales", editoriales);

        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestBody Libro libro, ModelMap model) throws MiException {

        if (libro == null)

            throw new MiException("Falta cuerpo del libro");


        try {

            libroServiceImpl.crearLibro(libro);

            model.put("exito", "El libro fue cargado exitosamente!");

        } catch (Exception ex) {

            Logger.getLogger(LibroController.class.getName()).log(Level.SEVERE, null, ex);

            List<Autor> autores = autorServiceImpl.listarAutores();
            List<Editorial> editoriales = editorialServiceImpl.listarEditoriales();

            model.addAttribute("autores", autores);

            model.addAttribute("editoriales", editoriales);

            model.put("error", ex.getMessage());

            return "libro_form.html";

        }

        return "libro_form.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap model) {

        model.addAttribute("libros", libroServiceImpl.listarLibros());
        return "libro_list";

    }

    @GetMapping("/editar/{isbn}")
    public String editar(ModelMap model, @PathVariable Long isbn) {

        model.put("libro", libroServiceImpl.getOne(isbn));

        List<Autor> autores = autorServiceImpl.listarAutores();
        List<Editorial> editoriales = editorialServiceImpl.listarEditoriales();

        model.addAttribute("autores", autores);

        model.addAttribute("editoriales", editoriales);

        return "libro_editar";

    }

    @PutMapping("/editar/{isbn}")
    public String modificar(@PathVariable Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial, ModelMap model) {

        try {
            libroServiceImpl.modificarLibro(isbn, titulo, idAutor, idEditorial, ejemplares);
            return "redirect:../lista";
        } catch (MiException e) {
            model.put("error", e.getMessage());

            List<Autor> autores = autorServiceImpl.listarAutores();
            List<Editorial> editoriales = editorialServiceImpl.listarEditoriales();

            model.addAttribute("autores", autores);

            model.addAttribute("editoriales", editoriales);

            return "libro_editar";
        }


    }

    @GetMapping
    public String libroPorId(@PathVariable Long isbn, ModelMap modelMap) throws MiException {

        if (isbn == 0)
            throw new MiException("Isbn no puede ser nulo");

        Libro libro = libroServiceImpl.getOne(isbn);

        modelMap.addAttribute("libro", libro);

        return "libro-detalles";


    }
}
