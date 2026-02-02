package com.gestionprotectorapro.controller;

import com.gestionprotectorapro.entity.Animal;
import com.gestionprotectorapro.repository.AnimalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")

public class AnimalController {

    private final AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    //Listar
    @GetMapping
    public List<Animal> listarAnimales() {
        return animalRepository.findAll();
    }


    //Insertar
    @PostMapping
    public Animal insertarAnimal(@RequestBody Animal animal){
        return animalRepository.save(animal);
    }

    //Listar animales no adoptados

    @GetMapping("/no-adoptados")
    public List<Animal> listarNoAdoptados() {
        return animalRepository.findByAdoptadoFalse();
    }

    //Actualizar animal
    @PutMapping("/{id}")
    public Animal actualizarAnimal(@PathVariable Long id, @RequestBody Animal animalActualizado) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));
        animal.setNombre(animalActualizado.getNombre());
        animal.setEspecie(animalActualizado.getEspecie());
        animal.setEdad(animalActualizado.getEdad());
        animal.setAdoptado(animalActualizado.isAdoptado());

        return animalRepository.save(animal);
    }

    //Eliminar animal

    @DeleteMapping("/{id}")
    public void eliminarAnimal(@PathVariable Long id) {
        animalRepository.deleteById(id);
    }

}
