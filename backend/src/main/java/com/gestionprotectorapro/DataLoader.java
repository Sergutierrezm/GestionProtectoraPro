
/*package com.gestionprotectorapro;

import com.gestionprotectorapro.entity.Animal;
import com.gestionprotectorapro.repository.AnimalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarDatos(AnimalRepository animalRepository){
        return args -> {

            //Insertar
            Animal a1 = new Animal ("Luna", "Perro", 3, false);
            Animal a2 = new Animal ("Michi", "Gato", 2, true);

            animalRepository.save(a1);
            animalRepository.save(a2);

            System.out.println("Animales insertados en la BD");


            //Listar
            System.out.println("Listado de animales:");

            animalRepository.findAll().forEach(animal -> {
                System.out.println(
                        animal.getId() + " " +
                        animal.getNombre() + " " +
                        animal.getEspecie() + " " +
                        animal.getEdad()  + " " +
                        "adoptado:" + animal.isAdoptado()
                );
            });
        };
    }

}

 */