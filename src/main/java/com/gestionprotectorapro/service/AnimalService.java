package com.gestionprotectorapro.service;

import com.gestionprotectorapro.dto.AnimalRequestDTO;
import com.gestionprotectorapro.dto.AnimalResponseDTO;
import com.gestionprotectorapro.entity.Animal;
import com.gestionprotectorapro.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

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

    public Page <Animal> listarTodos(Pageable pageable){

        return animalRepository.findAll(pageable);
    }
    //Listar no adoptadp
    public Page <Animal> listarNoAdoptados(Pageable pageable){
        return animalRepository.findByAdoptadoFalse(pageable);
    }

    //Eliminar
    public void eliminar(Long id){
        animalRepository.deleteById(id);
    }

    //Actualizar

    public Animal actualizar (Long id, AnimalRequestDTO dto){
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        animal.setNombre(dto.getNombre());
        animal.setEspecie(dto.getEspecie());
        animal.setEdad(dto.getEdad());
        animal.setAdoptado(dto.isAdoptado());

        return animalRepository.save(animal);
    }


    //Obtener un animal por ID

    public Optional<Animal> obtenerPorId(Long id){
        return animalRepository.findById(id);
    }

    //metodo para pasar a RequestDTO -> Entity
    private Animal convertirAEntity(AnimalRequestDTO dto) {
        Animal animal = new Animal();

        animal.setNombre(dto.getNombre());
        animal.setEspecie(dto.getEspecie());
        animal.setEdad(dto.getEdad());
        animal.setAdoptado(dto.isAdoptado());

        return animal;
    }


    //metodo para pasar de entity a ResponseDTO



    public AnimalResponseDTO crearAnimal (AnimalRequestDTO dto) {
        Animal animal = convertirAEntity(dto);
        Animal guardado = animalRepository.save(animal);
        return convertirAResponseDTO(guardado);


    }

    public AnimalResponseDTO convertirAResponseDTO(Animal animal){
        AnimalResponseDTO dto = new AnimalResponseDTO();
        dto.setId(animal.getId());
        dto.setNombre(animal.getNombre());
        dto.setEspecie(animal.getEspecie());
        dto.setEdad(animal.getEdad());
        dto.setAdoptado(animal.isAdoptado());
        return dto;
    }



    }





