
package com.egg.biblioteca.repositories;

import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.entities.Libro;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

    //@Query("SELECT l FROM Libro l WHERE l.titulo =:titulo")
    //public Libro buscarPorTitulo(@Param("titulo") String titulo);

    Optional<Libro> findByTitulo(String titulo);

    @Query("SELECT l FROM Libro l WHERE l.autor.nombre =:nombre")
    public List<Libro> buscarLibroPorAutor(@Param("nombre") String nombre);

    @Query("SELECT l FROM Libro l WHERE l.editorial.nombre =:nombre")
    public List<Editorial> buscarLibroPorEditorial(@Param("nombre") String nombre);

}
