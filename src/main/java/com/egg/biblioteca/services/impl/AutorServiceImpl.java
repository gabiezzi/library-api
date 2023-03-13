/*
// Curso Egg FullStack
 */
package com.egg.biblioteca.services.impl;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.repositories.AutorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.egg.biblioteca.services.AutorService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServiceImpl implements AutorService {
    
   @Autowired
    private AutorRepository autorRepository;

   @Override
    @Transactional
    public Autor crearAutor(Autor autor)  throws ServiceException {

        Optional<Autor> autorValidacion = autorRepository.findById(autor.getId());

        Optional<Autor> autorDuplicado = autorRepository.buscarAutorPorNombre(autor.getNombre());

       if (autorValidacion.isPresent())
            new ServiceException("Ya existe libro con ese id");

       if (autorDuplicado.isPresent())
           new ServiceException("Ya existe libro con ese nombre");

       return autorRepository.save(autor);
    }

    @Override
    public List<Autor> listarAutores() {

        List<Autor> autores = new ArrayList();

        autores = autorRepository.findAll();

        if (autores.isEmpty())
            throw new ServiceException("Authors list empty");

        return autores;

    }

    @Override
    @Transactional
    public Autor modificarAutor(Autor autor, String id) throws ServiceException {


        Autor respuestaAutor = autorRepository.findById(id).orElseThrow(()->new ServiceException("No hay autor con ese id"));

        return autorRepository.save(respuestaAutor);

    }

    @Override
    public Autor getOne(String id) throws ServiceException{
        return autorRepository.findById(id).orElseThrow(()->new ServiceException("No hay autor con ese id"));
    }

    @Override
    public void borrarAutor(String id) {

       autorRepository.delete(getOne(id));


    }


}
