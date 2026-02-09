package com.gestionprotectorapro.controller;

import com.gestionprotectorapro.dto.AnimalRequestDTO;
import com.gestionprotectorapro.dto.AnimalResponseDTO;
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
    public List<AnimalResponseDTO> listarAnimales() {
        return animalService.listarTodos()
                .stream()
                .map(animal -> animalService.convertirAResponseDTO(animal))
                .toList();
    }


    //Insertar
    @PostMapping
    public AnimalResponseDTO insertarAnimal(@Valid @RequestBody AnimalRequestDTO dto){
        return animalService.crearAnimal(dto);
    }

    //Listar animales no adoptados

    @GetMapping("/no-adoptados")
    public List<AnimalResponseDTO> listarNoAdoptados() {
        return animalService.listarNoAdoptados()
                .stream()
                .map(animalService::convertirAResponseDTO)
                .toList();
    }

    //Actualizar animal
    @PutMapping("/{id}")
    public AnimalResponseDTO actualizarAnimal(@PathVariable Long id,@Valid @RequestBody AnimalRequestDTO dto) {
        Animal actualizado = animalService.actualizar(id, dto);
        return animalService.convertirAResponseDTO(actualizado);
    }

    //Eliminar animal

    @DeleteMapping("/{id}")
    public void eliminarAnimal(@PathVariable Long id) {
        animalService.eliminar(id);
    }

    //obtener un animal por ID

    @GetMapping("/{id}")
    public AnimalResponseDTO obtenerPorId(@PathVariable Long id) {
        Animal animal = animalService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));
        return animalService.convertirAResponseDTO(animal);

    }

}
