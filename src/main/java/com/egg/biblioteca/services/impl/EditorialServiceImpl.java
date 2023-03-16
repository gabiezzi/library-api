/*
// Curso Egg FullStack
 */
package com.egg.biblioteca.services.impl;

import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.repositories.EditorialRepository;
import com.egg.biblioteca.exceptions.MiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.egg.biblioteca.services.EditorialService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* * @author Gabiezzi
 */
@Service
public class EditorialServiceImpl implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Override
    @Transactional
    public Editorial crearEditorial(Editorial editorial) throws ServiceException {

        Optional <Editorial> editorialValidacion = editorialRepository.findById(editorial.getId());

        Optional <Editorial> editorialDuplicada = editorialRepository.buscarEditorialPorNombre(editorial.getNombre());

        if (editorialValidacion.isPresent())
            new ServiceException("Ya hay una editorial con ese id");
        if (editorialDuplicada.isPresent())
            new ServiceException("Ya hay una editorial con ese nombre");

        return editorialRepository.save(editorial);
    }

    @Override
    public List<Editorial> listarEditoriales() {

        List<Editorial> editoriales = new ArrayList();

        editoriales = editorialRepository.findAll();

        return editoriales;
    }

    @Override
    @Transactional
    public Editorial modificarEditorial(Editorial editorial, String id) throws ServiceException {

        Editorial respuestaEditorial = editorialRepository.findById(id).orElseThrow(()-> new ServiceException("No hay editorial con ese id"));

        respuestaEditorial.setNombre(editorial.getNombre());

        return editorialRepository.save(respuestaEditorial);

    }

    @Override
    public Editorial getOne(String id) throws ServiceException{

        Editorial editorialRecibida = editorialRepository.findById(id).orElseThrow(()-> new ServiceException("No existe editorial con ese id"));

        return editorialRecibida;

    }

    @Override
    @Transactional
    public void borrarEditorial(String id){ editorialRepository.delete(getOne(id));   }


}
