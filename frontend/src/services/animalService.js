import axios from "axios";

// URL de tu backend Spring Boot
const API_URL = "http://localhost:8080/animals";

// Función para obtener todos los animales con paginación
export const getAnimals = (page = 0, size = 10) => {
  return axios.get(`${API_URL}?page=${page}&size=${size}`);
};

// Función para obtener solo animales no adoptados
export const getNonAdoptedAnimals = (page = 0, size = 10) => {
  return axios.get(`${API_URL}/no-adoptados?page=${page}&size=${size}`);
};

// Función para crear un animal
export const createAnimal = (animal) => {
  return axios.post(API_URL, animal);
};

// Función para actualizar un animal
export const updateAnimal = (id, animal) => {
  return axios.put(`${API_URL}/${id}`, animal);
};

// Función para eliminar un animal
export const deleteAnimal = (id) => {
  return axios.delete(`${API_URL}/${id}`);
};