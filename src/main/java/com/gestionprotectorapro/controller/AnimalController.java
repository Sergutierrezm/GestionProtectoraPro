package com.gestionprotectorapro.controller;

import com.gestionprotectorapro.dto.AnimalRequestDTO;
import com.gestionprotectorapro.dto.AnimalResponseDTO;
import com.gestionprotectorapro.entity.Animal;
import com.gestionprotectorapro.repository.AnimalRepository;
import com.gestionprotectorapro.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity <List<AnimalResponseDTO>> listarAnimales() {
        List<AnimalResponseDTO> animales = animalService.listarTodos()
                .stream()
                .map(animalService::convertirAResponseDTO)
                .toList();
        return ResponseEntity.ok(animales);

    }


    //Insertar
    @PostMapping
    public ResponseEntity<AnimalResponseDTO> insertarAnimal(@Valid @RequestBody AnimalRequestDTO dto){
        AnimalResponseDTO creado = animalService.crearAnimal(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    //Listar animales no adoptados

    @GetMapping("/no-adoptados")
    public ResponseEntity <List<AnimalResponseDTO>> listarNoAdoptados() {
        List<AnimalResponseDTO> animales = animalService.listarNoAdoptados()
                .stream()
                .map(animalService::convertirAResponseDTO)
                .toList();
        return ResponseEntity.ok(animales);
    }

    //Actualizar animal
    @PutMapping("/{id}")
    public ResponseEntity <AnimalResponseDTO> actualizarAnimal(@PathVariable Long id,@Valid @RequestBody AnimalRequestDTO dto) {
        Animal actualizado = animalService.actualizar(id, dto);
        return ResponseEntity.ok (animalService.convertirAResponseDTO(actualizado));
    }

    //Eliminar animal

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAnimal (@PathVariable Long id) {
        animalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //obtener un animal por ID

    @GetMapping("/{id}")
    public ResponseEntity <AnimalResponseDTO> obtenerPorId(@PathVariable Long id) {
        Animal animal = animalService.obtenerPorId(id)
                .orElseThrow(() -> new AnimalNotFoundException("Animal no encontrado"));
        return ResponseEntity.ok (animalService.convertirAResponseDTO(animal));

    }

}
