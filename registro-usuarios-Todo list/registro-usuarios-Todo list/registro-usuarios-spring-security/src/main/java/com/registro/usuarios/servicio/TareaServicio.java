package com.registro.usuarios.servicio;

import com.registro.usuarios.modelo.Tarea;
import com.registro.usuarios.repositorio.TareaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServicio {

    @Autowired
    private TareaRepositorio tareaRepositorio;

    public List<Tarea> listarTareas() {
        return tareaRepositorio.findAll();
    }

    public void crearTarea(Tarea tarea) {
        tareaRepositorio.save(tarea);
    }

    public Tarea obtenerTareaPorId(Long id) {
        Optional<Tarea> tarea = tareaRepositorio.findById(id);
        return tarea.orElse(null);
    }

    public void editarTarea(Long id, Tarea tarea) {
        tarea.setId(id); // Asegúrate de que el ID esté seteado antes de guardar
        tareaRepositorio.save(tarea);
    }

    public void eliminarTarea(Long id) {
        tareaRepositorio.deleteById(id);
    }
}
