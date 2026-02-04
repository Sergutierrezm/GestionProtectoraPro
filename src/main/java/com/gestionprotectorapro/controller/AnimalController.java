package com.gestionprotectorapro.controller;

import com.gestionprotectorapro.entity.Animal;
import com.gestionprotectorapro.repository.AnimalRepository;
import com.gestionprotectorapro.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")

public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService){
        this.animalService = animalService;
    }

    //Listar
    @GetMapping
    public List<Animal> listarAnimales() {
        return animalService.listarTodos();
    }


    //Insertar
    @PostMapping
    public Animal insertarAnimal(@Valid @RequestBody Animal animal){
        return animalService.guardar(animal);
    }

    //Listar animales no adoptados

    @GetMapping("/no-adoptados")
    public List<Animal> listarNoAdoptados() {
        return animalService.listarNoAdoptados();
    }

    //Actualizar animal
    @PutMapping("/{id}")
    public Animal actualizarAnimal(@PathVariable Long id, @RequestBody Animal animalActualizado) {

        return animalService.actualizar(id, animalActualizado);
    }

    //Eliminar animal

    @DeleteMapping("/{id}")
    public void eliminarAnimal(@PathVariable Long id) {
        animalService.eliminar(id);
    }

}
