package com.gestionprotectorapro.repository;


import com.gestionprotectorapro.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    //Animales no adoptados

    List<Animal> findByAdoptadoFalse();



}
