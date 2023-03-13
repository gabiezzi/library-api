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
import org.springframework.http.ResponseEntity;
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

    //Create book
    @PostMapping("/crear")
    public ResponseEntity<Libro> registro(Libro libro) throws MiException {

       if (libro == null)
           return ResponseEntity.badRequest().build();

       return ResponseEntity.ok(libroServiceImpl.crearLibro(libro));
    }

    //Get all books

    @GetMapping("/lista")

    public List<Libro> listar() {

        return libroServiceImpl.listarLibros();

    }

    //get one book by Isbn
    @GetMapping("/{id}")
    public ResponseEntity<Libro> libroPorId(@PathVariable(value="id") Long isbn) throws MiException {

        if (isbn == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(libroServiceImpl.getOne(isbn));


    }


    @PostMapping("/editar/{id}")
    public ResponseEntity<Libro> editar(Libro libroActualizado, @PathVariable Long isbn) {

        if (isbn == null || libroActualizado == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(libroServiceImpl.editarLibro(isbn, libroActualizado));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Libro> borrarLibro(@PathVariable(value = "id") Long isbn){

        if (isbn == null)
            ResponseEntity.badRequest().build();

        return ResponseEntity.noContent().build();

    }




}
