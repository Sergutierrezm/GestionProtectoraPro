package com.gestionprotectorapro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "animal")

public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "La especie es obligatoria")
    private String especie;
    @Min(value = 0, message = "La edad no puede ser negativa")
    private int edad;
    private boolean adoptado;


    //Constructor vacio
    public Animal() {

    }


    public Animal(String nombre, String especie, int edad, boolean adoptado) {
        this.nombre= nombre;
        this.especie = especie;
        this.edad = edad;
        this.adoptado = adoptado;
    }

    //Getters y setters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad (int edad) {
        this.edad = edad;
    }

    public boolean isAdoptado() {
        return adoptado;
    }

    public void setAdoptado(boolean adoptado) {
        this.adoptado = adoptado;
    }

}
