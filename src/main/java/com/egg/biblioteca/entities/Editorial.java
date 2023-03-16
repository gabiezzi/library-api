/*
// Curso Egg FullStack
 */

package com.egg.biblioteca.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/* * @author Gabiezzi
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Editorial {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;

    public Editorial(String nombre) {
        this.nombre = nombre;
    }
}
