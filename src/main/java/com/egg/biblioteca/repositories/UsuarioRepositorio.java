/*
// Curso Egg FullStack
 */

package com.egg.biblioteca.repositories;

import com.egg.biblioteca.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/* * @author Gabiezzi
 */

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u WHERE u.email =:email")
    public Usuario buscarPorEmail(@Param("email") String email);
}

