package com.egg.biblioteca.services.impl;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.entities.Libro;
import com.egg.biblioteca.repositories.AutorRepository;
import com.egg.biblioteca.repositories.EditorialRepository;
import com.egg.biblioteca.repositories.LibroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest -> recibe peticiones htttp // web enviroment puerto aleatorio
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class LibroServiceImplUnitTest {


    final LibroServiceImpl libroService;

    final LibroRepository libroRepository;

    final AutorRepository autorRepository;

    final EditorialRepository editorialRepository;

    @Autowired
    public LibroServiceImplUnitTest(LibroServiceImpl libroService, LibroRepository libroRepository, AutorRepository autorRepository, EditorialRepository editorialRepository) {
        this.libroService = libroService;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.editorialRepository = editorialRepository;
    }

    @Test
    void crearLibro() {

        //Arrange
        Libro libro1 = new Libro();
        libro1.setTitulo("El señor de los anillos");
        libro1.setCantEjemplares(1000);
        libro1.setIsbn(3490349L);

        Autor autor = new Autor();
        autor.setNombre("Robert Robertson");
        autorRepository.save(autor);

        Editorial editorial1 = new Editorial();
        editorial1.setNombre("Panamericana");
        editorialRepository.save(editorial1);

        libro1.setAutor(autor);
        libro1.setEditorial(editorial1);
        libro1.setAlta(LocalDateTime.now());

        //Act
        Libro libroGuardado = libroService.crearLibro(libro1);

        //Assert

        assertEquals(libro1.getIsbn(), libroGuardado.getIsbn());
        assertEquals(libro1.getAlta(), libroGuardado.getAlta());
        assertEquals(libro1.getCantEjemplares(), libroGuardado.getCantEjemplares());


    }

    @Test
    void listarLibros() {


        Autor autor1 = new Autor("Antonio Rivas");
        Autor autor2 = new Autor("Roberto Hidrogenion");
        Autor autor3 = new Autor("Carlos Sastre");
        Autor autor4 = new Autor("Melina Angie");
        autorRepository.saveAll(Arrays.asList(autor1,autor2,autor3,autor4));

        Editorial editorial1 = new Editorial("Panamericana");
        Editorial editorial2 = new Editorial("Pinguino");
        Editorial editorial3 = new Editorial("El isleño");
        Editorial editorial4 = new Editorial("Pimpumpim");
        editorialRepository.saveAll(Arrays.asList(editorial1,editorial2,editorial3,editorial4));

        Libro libro1 = new Libro(1L,"Harry el sucio", 1040, LocalDateTime.now(), autor1, editorial1);
        Libro libro2 = new Libro(2L,"El reloj giratorio", 300, LocalDateTime.now(), autor2, editorial2);
        Libro libro3 = new Libro(3L,"Autoanalisis de un asesino", 400, LocalDateTime.now(), autor3, editorial3);
        Libro libro4 = new Libro(4L,"El cargador del tiempo", 1100, LocalDateTime.now(), autor4, editorial4);

        List libros = Arrays.asList(libro1, libro2, libro3, libro4);
        libroRepository.saveAll(libros);

        List<Libro> librosGuardados = libroService.listarLibros();


        assertEquals(libros.size(), librosGuardados.size());

       assertEquals(libro1.getIsbn(),librosGuardados.get(0).getIsbn());
        assertEquals(libro1.getTitulo(),librosGuardados.get(0).getTitulo());

        assertEquals(libro2.getIsbn(),librosGuardados.get(1).getIsbn());
        assertEquals(libro2.getTitulo(),librosGuardados.get(1).getTitulo());

        assertEquals(libro3.getIsbn(),librosGuardados.get(2).getIsbn());
        assertEquals(libro3.getTitulo(),librosGuardados.get(2).getTitulo());

        assertEquals(libro4.getIsbn(),librosGuardados.get(3).getIsbn());
        assertEquals(libro4.getTitulo(),librosGuardados.get(3).getTitulo());


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