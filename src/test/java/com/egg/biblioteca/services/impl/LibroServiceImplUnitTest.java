package com.egg.biblioteca.services.impl;

import com.egg.biblioteca.entities.Libro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureTestDatabase
class LibroServiceImplUnitTest {

    @Autowired
    LibroServiceImpl libroService;

    @Test
    void crearLibro() {

        //Arrange
        Libro libro1 = new Libro();
        libro1.setTitulo("El señor de los anillos");
        libro1.setCantEjemplares(1000);
        libro1.setIsbn(3490349L);
        libro1.setAutor(null);
        libro1.setAutor(null);
        libro1.setAlta(LocalDateTime.now());

        //Act
        Libro libroGuardado = libroService.crearLibro(libro1);

        //Assert




    }

    @Test
    void listarLibros() {
    }

    @Test
    void editarLibro() {
    }

    @Test
    void getOne() {
    }

    @Test
    void borrarLibro() {
    }
}