/*
// Curso Egg FullStack
 */

package com.egg.biblioteca.repositories;

import com.egg.biblioteca.entities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/* * @author Gabiezzi
 */

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, String>{
    
    @Query ("SELECT e FROM Editorial e WHERE e.nombre =:nombre")
    public Editorial buscarEditorialPorNombre(@Param("nombre") String nombre);

    
    
}
