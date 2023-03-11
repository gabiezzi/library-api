package com.egg.biblioteca.services.impl;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.entities.Libro;
import com.egg.biblioteca.repositories.AutorRepository;
import com.egg.biblioteca.repositories.EditorialRepository;
import com.egg.biblioteca.repositories.LibroRepository;
import com.egg.biblioteca.exceptions.MiException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServiceImpl {

    private final LibroRepository libroRepository;

    private final AutorRepository autorRepository;

    private final EditorialRepository editorialRepository;

    @Autowired
    public LibroServiceImpl(LibroRepository libroRepository, AutorRepository autorRepository, EditorialRepository editorialRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.editorialRepository = editorialRepository;
    }

    @Transactional
    public void crearLibro(Libro libro) throws ServiceException {

        validarLibro(libro);

        if (libroRepository.findByTitulo(libro.getTitulo()).isPresent())
            throw new ServiceException("Este libro ya existe en BBDD");

        Autor autor = autorRepository.findById(libro.getAutor().getId()).orElseThrow(() -> new ServiceException("Autor no encontrado"));

        Editorial editorial = editorialRepository.findById(libro.getEditorial().getId()).orElseThrow(() -> new ServiceException("Editorial no encontrada"));


        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setAlta(LocalDateTime.now());

        libroRepository.save(libro);

    }

    public List<Libro> listarLibros() throws ServiceException {

        List<Libro> libros = libroRepository.findAll();

        if (libros.isEmpty())
            throw new ServiceException("Lista vacia");

        return libros;

    }


    @Transactional
    public void modificarLibro(Long isbn, Libro libroActualizado) throws ServiceException {

        validarLibro(libroActualizado);
        Libro libro = libroRepository.findById(isbn).orElseThrow(() -> new ServiceException("Libro a actualizar no encontrado"));

        libro.setTitulo(libroActualizado.getTitulo());

        libro.setAutor(autorRepository.findById(libroActualizado.getAutor().getId()).orElseThrow(() -> new ServiceException("Autor no encontrado")));

        libro.setEditorial(editorialRepository.findById(libroActualizado.getEditorial().getId()).orElseThrow(() -> new ServiceException("Editorial no encontrada")));

        libro.setCantEjemplares(libroActualizado.getCantEjemplares());


        libroRepository.save(libro);


    }

    public Libro getOne(Long isbn) {
        return libroRepository.findById(isbn).orElseThrow(() -> new ServiceException("Libro no existente"));
    }

    private void validarLibro(Libro libro) throws ServiceException {

        if (libro.getIsbn() == null) {
            throw new ServiceException("El isbn no puede ser nulo.");
        }

        if (libro.getTitulo().isEmpty() || libro.getTitulo() == null) {
            throw new ServiceException("Titulo no puede ser nulo o estar vacío.");
        }
        if (libro.getCantEjemplares() == null) {
            throw new ServiceException("Numero de ejemplares no puede ser nulo.");
        }

        if (libro.getAutor().getId() == null || libro.getAutor() == null) {
            throw new ServiceException("Autor no puede ser nulo.");
        }
        if (libro.getEditorial() == null || libro.getEditorial().getId() == null) {
            throw new ServiceException("Editorial no puede ser nulo.");
        }
    }

}
