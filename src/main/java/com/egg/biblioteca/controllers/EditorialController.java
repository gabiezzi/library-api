/*
// Curso Egg FullStack
 */

package com.egg.biblioteca.controllers;

import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.exceptions.MiException;
import com.egg.biblioteca.services.impl.EditorialServiceImpl;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/editorial/")
public class EditorialController {

    @Autowired
    EditorialServiceImpl editorialServiceImpl;

    @PostMapping()
    public ResponseEntity<Editorial> crearEditorial(@RequestBody Editorial editorial) {

        if (editorial == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(editorialServiceImpl.crearEditorial(editorial));

    }

    @GetMapping("/listar")
    public ResponseEntity<List<Editorial>> listarEditoriales() {

        return ResponseEntity.ok(editorialServiceImpl.listarEditoriales());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Editorial> modificarEditorial(@PathVariable(value = "id") String idEditorial, Editorial editorial) throws ServiceException {

        if (idEditorial == null || editorial == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(editorialServiceImpl.modificarEditorial(editorial, idEditorial));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Editorial> getOne(@PathVariable(value = "id") String idEditorial) {

        if (idEditorial == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(editorialServiceImpl.getOne(idEditorial));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Editorial> borrarEditorial(@PathVariable(value = "id") String idEditorial) {

        if (idEditorial == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.noContent().build();

    }


}
