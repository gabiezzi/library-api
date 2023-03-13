/*
// Curso Egg FullStack
 */
package com.egg.biblioteca.controllers;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.exceptions.MiException;
import com.egg.biblioteca.services.impl.AutorServiceImpl;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/autor") //localhost:8080/autor
public class AutorController {

    @Autowired
    private AutorServiceImpl autorServiceImpl;

    @PostMapping()
    public ResponseEntity<Autor> crear(@RequestBody Autor autor) { //mismo nombre que el que tiene el parametro de form name=""

        if (autor == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(autorServiceImpl.crearAutor(autor));


    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> editar(@PathVariable String id, @RequestBody Autor autor) throws MiException {

        if (autor == null || id == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(autorServiceImpl.modificarAutor(autor, id));

    }

    @GetMapping("/lista")
    public ResponseEntity<List<Autor>> listar() {

        return ResponseEntity.ok(autorServiceImpl.listarAutores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> getOne(@PathVariable(value = "id") String idAutor){

        if (idAutor == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(autorServiceImpl.getOne(idAutor));

    }

    @DeleteMapping
    public ResponseEntity<Autor> borrarLibro(@PathVariable(value = "id") String idAutor){

        if (idAutor == null)
            return ResponseEntity.badRequest().build();

        autorServiceImpl.borrarAutor(idAutor);

        return ResponseEntity.noContent().build();

    }

}
