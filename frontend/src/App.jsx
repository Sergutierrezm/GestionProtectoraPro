import { useState, useEffect } from "react";
import { getAnimals, createAnimal, deleteAnimal, updateAnimal } from "./services/animalService";

function App() {
  const [animales, setAnimales] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Estado formulario para añadir animal
  const [form, setForm] = useState({
    nombre: "",
    especie: "",
    edad: "",
    adoptado: false
  });

  // Estado edición
  const [editingId, setEditingId] = useState(null);
  const [editForm, setEditForm] = useState({
    nombre: "",
    especie: "",
    edad: "",
    adoptado: false
  });

  // Traer animales desde backend
  useEffect(() => {
    getAnimals()
      .then(response => {
        setAnimales(response.data.content);
        setLoading(false);
      })
      .catch(err => {
        console.error("Error al traer animales:", err);
        setError("No se pudieron cargar los animales");
        setLoading(false);
      });
  }, []);

  // Manejo cambios formulario añadir
  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm(prev => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value
    }));
  };

  // Manejo cambios formulario editar
  const handleEditChange = (e) => {
    const { name, value, type, checked } = e.target;
    setEditForm(prev => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value
    }));
  };

  // Añadir animal
  const handleSubmit = (e) => {
    e.preventDefault();
    createAnimal({
      nombre: form.nombre,
      especie: form.especie,
      edad: parseInt(form.edad),
      adoptado: form.adoptado
    })
      .then(res => {
        setAnimales(prev => [...prev, res.data]);
        setForm({ nombre: "", especie: "", edad: "", adoptado: false });
      })
      .catch(err => console.error("Error creando animal:", err));
  };

  // Borrar animal
  const handleDelete = (id) => {
    deleteAnimal(id)
      .then(() => {
        setAnimales(prev => prev.filter(a => a.id !== id));
      })
      .catch(err => console.error("Error borrando animal:", err));
  };

  // Empezar a editar
  const startEditing = (animal) => {
    setEditingId(animal.id);
    setEditForm({
      nombre: animal.nombre,
      especie: animal.especie,
      edad: animal.edad,
      adoptado: animal.adoptado
    });
  };

  // Guardar edición
  const handleUpdate = (e) => {
    e.preventDefault();
    updateAnimal(editingId, editForm)
      .then(res => {
        setAnimales(prev => prev.map(a => a.id === editingId ? res.data : a));
        setEditingId(null);
      })
      .catch(err => console.error("Error actualizando animal:", err));
  };

  if (loading) return <div>Cargando animales...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div>
      <h1>Gestión Protectora</h1>

      {/* Formulario añadir */}
      <form onSubmit={handleSubmit}>
        <input 
          name="nombre" 
          value={form.nombre} 
          onChange={handleChange} 
          placeholder="Nombre" 
          required 
        />
        <input 
          name="especie" 
          value={form.especie} 
          onChange={handleChange} 
          placeholder="Especie" 
          required 
        />
        <input 
          name="edad" 
          type="number"
          value={form.edad} 
          onChange={handleChange} 
          placeholder="Edad" 
          required 
        />
        <label>
          Adoptado
          <input 
            name="adoptado" 
            type="checkbox" 
            checked={form.adoptado} 
            onChange={handleChange} 
          />
        </label>
        <button type="submit">Añadir Animal</button>
      </form>

      {/* Tabla */}
      <table border="1" cellPadding="8">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Especie</th>
            <th>Edad</th>
            <th>Adoptado</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {animales.map(animal => (
            <tr key={animal.id}>
              <td>{animal.id}</td>
              <td>
                {editingId === animal.id ? (
                  <input 
                    name="nombre"
                    value={editForm.nombre}
                    onChange={handleEditChange}
                  />
                ) : animal.nombre}
              </td>
              <td>
                {editingId === animal.id ? (
                  <input 
                    name="especie"
                    value={editForm.especie}
                    onChange={handleEditChange}
                  />
                ) : animal.especie}
              </td>
              <td>
                {editingId === animal.id ? (
                  <input 
                    name="edad"
                    type="number"
                    value={editForm.edad}
                    onChange={handleEditChange}
                  />
                ) : animal.edad}
              </td>
              <td>
                {editingId === animal.id ? (
                  <input 
                    name="adoptado"
                    type="checkbox"
                    checked={editForm.adoptado}
                    onChange={handleEditChange}
                  />
                ) : (animal.adoptado ? "Sí" : "No")}
              </td>
              <td>
                {editingId === animal.id ? (
                  <button onClick={handleUpdate}>Guardar</button>
                ) : (
                  <button onClick={() => startEditing(animal)}>Editar</button>
                )}
                <button onClick={() => handleDelete(animal.id)}>Borrar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;