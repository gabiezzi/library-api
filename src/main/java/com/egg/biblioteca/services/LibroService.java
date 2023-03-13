package com.egg.biblioteca.services;

import com.egg.biblioteca.entities.Libro;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public interface LibroService {

    Libro crearLibro(Libro libro) throws ServiceException;

    List<Libro> listarLibros() throws ServiceException;

    Libro editarLibro(Long isbn, Libro libroActualizado) throws ServiceException;

    Libro getOne(Long isbn);

    void borrarLibro(Long isbn);


}
