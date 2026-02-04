package com.gestionprotectorapro.service;

import com.gestionprotectorapro.entity.Animal;
import com.gestionprotectorapro.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    //Insertar
    public Animal guardar(Animal animal){
        return animalRepository.save(animal);
    }

    //Listar todos

    public List<Animal> listarTodos(){
        return animalRepository.findAll();
    }
    //Listar no adoptadp
    public List<Animal> listarNoAdoptados(){
        return animalRepository.findByAdoptadoFalse();
    }

    //Eliminar
    public void eliminar(Long id){
        animalRepository.deleteById(id);
    }

    //Actualizar

    public Animal actualizar (Long id, Animal animalActualizado){
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        animal.setNombre(animalActualizado.getNombre());
        animal.setEspecie(animalActualizado.getEspecie());
        animal.setEdad(animalActualizado.getEdad());
        animal.setAdoptado(animalActualizado.isAdoptado());

        return animalRepository.save(animal);
    }


}
