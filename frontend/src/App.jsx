import { useState, useEffect } from "react";
import { getAnimals, createAnimal, deleteAnimal, updateAnimal } from "./services/animalService";
import "./App.css";

function App() {
  const [animales, setAnimales] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [user, setUser] = useState("");
  const [password, setPassword] = useState("");
  const [loggedIn, setLoggedIn] = useState(false);

  const [form, setForm] = useState({ nombre: "", especie: "", edad: "", adoptado: false });
  const [editingId, setEditingId] = useState(null);
  const [editForm, setEditForm] = useState({ nombre: "", especie: "", edad: "", adoptado: false });

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

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm(prev => ({ ...prev, [name]: type === "checkbox" ? checked : value }));
  };

  const handleEditChange = (e) => {
    const { name, value, type, checked } = e.target;
    setEditForm(prev => ({ ...prev, [name]: type === "checkbox" ? checked : value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    createAnimal({ nombre: form.nombre, especie: form.especie, edad: parseInt(form.edad), adoptado: form.adoptado })
      .then(res => {
        setAnimales(prev => [...prev, res.data]);
        setForm({ nombre: "", especie: "", edad: "", adoptado: false });
      })
      .catch(err => console.error("Error creando animal:", err));
  };

  const handleDelete = (id) => {
    deleteAnimal(id)
      .then(() => setAnimales(prev => prev.filter(a => a.id !== id)))
      .catch(err => console.error("Error borrando animal:", err));
  };

  const startEditing = (animal) => {
    setEditingId(animal.id);
    setEditForm({ nombre: animal.nombre, especie: animal.especie, edad: animal.edad, adoptado: animal.adoptado });
  };

  const handleUpdate = (e) => {
    e.preventDefault();
    updateAnimal(editingId, editForm)
      .then(res => setAnimales(prev => prev.map(a => a.id === editingId ? res.data : a)))
      .catch(err => console.error("Error actualizando animal:", err));
    setEditingId(null);
  };

  const handleLogin = (e) => {
    e.preventDefault();
    if(user === "admin" && password === "1234"){
      setLoggedIn(true);
    } else {
      alert("Usuario o contraseña incorrectos");
    }
  };

  const handleLogout = () => {
    setLoggedIn(false);
    setUser("");
    setPassword("");
  };

  // ------------------ LOGIN ------------------
  if (!loggedIn) {
    return (
      <div className="login-page">
        <div className="login-wrapper">
          <div className="login-container">
            <h1>Gestión Protectora</h1>
            <p>Inicia sesión para continuar</p>
            <form onSubmit={handleLogin}>
              <input placeholder="Usuario" value={user} onChange={(e) => setUser(e.target.value)} required />
              <input type="password" placeholder="Contraseña" value={password} onChange={(e) => setPassword(e.target.value)} required />
              <button type="submit">Entrar</button>
            </form>
          </div>
        </div>
      </div>
    );
  }

  // ------------------ DASHBOARD ------------------
  if (loading) return <div>Cargando animales...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div className="dashboard">
      {/* Header con título y botón salir */}
      <div className="dashboard-header">
        <div>
        <h1>Gestión Protectora</h1>
        <p className="dashboard-subtitle">Panel de control de animales</p>
        </div>
        <button className="logout" onClick={handleLogout}>Salir</button>
      </div>
  
      {/* Formulario en mini-card */}
      <div className="dashboard-section">
        <form onSubmit={handleSubmit}>
          <input name="nombre" value={form.nombre} onChange={handleChange} placeholder="Nombre" required />
          <input name="especie" value={form.especie} onChange={handleChange} placeholder="Especie" required />
          <input name="edad" type="number" value={form.edad} onChange={handleChange} placeholder="Edad" required />
          <label>
            Adoptado
            <input name="adoptado" type="checkbox" checked={form.adoptado} onChange={handleChange} />
          </label>
          <button type="submit">Añadir Animal</button>
        </form>
      </div>
  
      {/* Tabla en mini-card */}
      <div className="dashboard-section">
        <table>
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
                <td>{editingId === animal.id ? <input name="nombre" value={editForm.nombre} onChange={handleEditChange} /> : animal.nombre}</td>
                <td>{editingId === animal.id ? <input name="especie" value={editForm.especie} onChange={handleEditChange} /> : animal.especie}</td>
                <td>{editingId === animal.id ? <input name="edad" type="number" value={editForm.edad} onChange={handleEditChange} /> : animal.edad}</td>
                <td>{editingId === animal.id ? <input name="adoptado" type="checkbox" checked={editForm.adoptado} onChange={handleEditChange} /> : (animal.adoptado ? "Sí" : "No")}</td>
                <td>
                  {editingId === animal.id ? <button onClick={handleUpdate}>Guardar</button> : <button onClick={() => startEditing(animal)}>Editar</button>}
                  <button onClick={() => handleDelete(animal.id)}>Borrar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default App;