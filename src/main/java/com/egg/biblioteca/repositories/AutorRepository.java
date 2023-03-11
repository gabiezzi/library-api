/*
// Curso Egg FullStack
 */
package com.egg.biblioteca.repositories;

import com.egg.biblioteca.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AutorRepository extends JpaRepository<Autor, String> {
    
    @Query ("SELECT a FROM Autor a WHERE a.nombre=: nombre")
    public Autor buscarAutorPorNombre(@Param("nombre") String nombre);

    
}

