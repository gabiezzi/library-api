/*
// Curso Egg FullStack
 */

package com.egg.biblioteca.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/* * @author Gabiezzi
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    
    @Id
    private Long isbn;
    private String titulo;
    private Integer cantEjemplares;

    private LocalDateTime alta;
    
    @ManyToOne
    private Autor autor;
    
    @ManyToOne
    private Editorial editorial;

}
