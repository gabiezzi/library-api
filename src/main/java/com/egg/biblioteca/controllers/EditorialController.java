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


}
