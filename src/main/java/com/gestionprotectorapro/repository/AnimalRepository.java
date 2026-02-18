package com.gestionprotectorapro.repository;


import com.gestionprotectorapro.entity.Animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    //Animales no adoptados

    Page<Animal> findByAdoptadoFalse(Pageable pageable);



}
