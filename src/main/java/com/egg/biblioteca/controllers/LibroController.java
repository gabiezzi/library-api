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

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/* * @author Gabiezzi
 */
@Controller
@RequestMapping("libro/")
public class LibroController {

    private final LibroServiceImpl libroServiceImpl;
    private final AutorServiceImpl autorServiceImpl;
    private final EditorialServiceImpl editorialServiceImpl;

    @Autowired
    public LibroController(LibroServiceImpl libroServiceImpl, AutorServiceImpl autorServiceImpl, EditorialServiceImpl editorialServiceImpl) {
        this.libroServiceImpl = libroServiceImpl;
        this.autorServiceImpl = autorServiceImpl;
        this.editorialServiceImpl = editorialServiceImpl;
    }

    @GetMapping("/registrar")
    public String registrar(Model model) {

        List<Autor> autores = autorServiceImpl.listarAutores();
        List<Editorial> editoriales = editorialServiceImpl.listarEditoriales();

        model.addAttribute("libro", new Libro());
        model.addAttribute("autores", autores);
        model.addAttribute("editoriales", editoriales);

        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute("libro") @Valid Libro libro, BindingResult result, Model model) throws MiException {

        if (result.hasErrors()) {
            model.addAttribute("autores", autorServiceImpl.listarAutores());
            model.addAttribute("editoriales", editorialServiceImpl.listarEditoriales());
            return "libro_form";
        }

        libroServiceImpl.crearLibro(libro);
        model.addAttribute("exito", "El libro fue cargado exitosamente!");
        return "redirect:/libro/lista";

    }

    @GetMapping("/lista")
    public String listar(Model model) {

        model.addAttribute("libros", libroServiceImpl.listarLibros());
        return "libro_list";

    }

    @GetMapping("/editar/{isbn}")
    public String editar(@PathVariable Long isbn, Model model) throws MiException {

        if (isbn == null)
            throw new MiException("Isbn no púede ser nulo");

        model.addAttribute("libro", libroServiceImpl.getOne(isbn));

        model.addAttribute("autores", autorServiceImpl.listarAutores());

        model.addAttribute("editoriales", editorialServiceImpl.listarEditoriales());

        return "libro_editar";

    }

    @PostMapping("/editar/{isbn}")
    public String modificar(@ModelAttribute("libro") @Valid Libro libroActualizado, BindingResult result, Model model, @PathVariable Long isbn) {

        if (result.hasErrors()) {

            model.addAttribute("autores", autorServiceImpl.listarAutores());

            model.addAttribute("editoriales", editorialServiceImpl.listarEditoriales());

            return "libro_editar";
        }

        try {
            libroServiceImpl.modificarLibro(isbn, libroActualizado);

            return "redirect:/libro/lista";

        } catch (ServiceException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("autores", autorServiceImpl.listarAutores());
            model.addAttribute("editoriales", editorialServiceImpl.listarEditoriales());
            return "libro_editar";
        }


    }

    @GetMapping
    public String libroPorId(@PathVariable Long isbn, Model modelMap) throws MiException {

        if (isbn == null)
            throw new MiException("Isbn no puede ser nulo");

        Libro libro = libroServiceImpl.getOne(isbn);

        modelMap.addAttribute("libro", libro);

        return "libro-detalles";


    }
}
