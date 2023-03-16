package com.egg.biblioteca.services;

import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.exceptions.MiException;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public interface EditorialService {

    Editorial crearEditorial(Editorial editorial) throws ServiceException;

    List<Editorial> listarEditoriales();

    Editorial modificarEditorial(Editorial editorial, String id) throws MiException;

    Editorial getOne(String id);

    void borrarEditorial(String id);



}
