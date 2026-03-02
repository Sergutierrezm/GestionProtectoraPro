# GestionProtectoraPro

**GestionProtectoraPro** es una aplicación web para gestionar animales en una protectora.  
Permite añadir, editar, eliminar y listar animales, y marcar si están adoptados o no. Incluye un panel de administración con login simple.

---

## Tecnologías

- **Backend:** Java, Spring Boot, JPA, Hibernate, MySQL
- **Frontend:** React, Vite, Axios, CSS
- **Despliegue:** Render (backend), Netlify (frontend)

---

## Funcionalidades

- **Login sencillo:** Usuario `admin`, Contraseña `1234`.
- **Gestión de animales:** Añadir, editar y eliminar animales.
- **Estados de adopción:** Marcar animales como adoptados.
- **Listados:**
    - Todos los animales (paginados)
    - Animales no adoptados

---

## 🚀 Cómo probar la aplicación

1. Abrir la demo en Netlify: [https://gestionprotectorapro.netlify.app](https://gestionprotectorapro.netlify.app)
2. Iniciar sesión con:
- **Usuario:** admin
- **Contraseña:** 1234

---


## Endpoints principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/animals` | Lista todos los animales paginados |
| GET | `/animals/no-adoptados` | Lista animales no adoptados |
| GET | `/animals/{id}` | Obtiene un animal por ID |
| POST | `/animals` | Crea un animal |
| PUT | `/animals/{id}` | Actualiza un animal |
| DELETE | `/animals/{id}` | Elimina un animal |

---

## Despliegue

- **Frontend:** [https://gestionprotectorapro.netlify.app](https://gestionprotectorapro.netlify.app)
- **Backend:** [https://gestionprotectorapro.onrender.com](https://gestionprotectorapro.onrender.com)

> La aplicación funciona directamente desde estas URLs y no requiere instalación.

---

## Notas técnicas

- Se utilizó **DTOs** (`AnimalRequestDTO` y `AnimalResponseDTO`) para separar la entidad de la API y facilitar la validación y respuesta.
- Se implementó **WebConfig** para manejar **CORS** y permitir que el frontend pueda hacer peticiones al backend desde Netlify.
- La paginación se maneja con Spring Data (`Pageable`) para listas grandes de animales.
- **Spring JPA** con `hibernate.ddl-auto=update` permite que la estructura de la base de datos se cree automáticamente según las entidades.
- Axios se utiliza en el frontend para consumir los endpoints del backend.

---

## Notas

- La base de datos gratuita de Railway dura 30 días.
- Para producción estable, se recomienda usar un servicio de base de datos gestionado (MySQL/PostgreSQL).

---

## Autor

Sergio Gutiérrez – [GitHub](https://github.com/sergiogutierrez370)