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

}
