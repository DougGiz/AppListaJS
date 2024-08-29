package com.registro.usuarios.controlador;

import com.registro.usuarios.modelo.Tarea;
import com.registro.usuarios.servicio.TareaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tareas")
public class TareaControlador {

    @Autowired
    private TareaServicio tareaServicio;

    @GetMapping
    public String listarTareas(Model model) {
        List<Tarea> tareas = tareaServicio.listarTareas();
        model.addAttribute("tareas", tareas);
        return "lista-tareas";
    }


    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("tarea", new Tarea());
        return "formulario-tarea";
    }

    @PostMapping("/guardar")
    public String guardarTarea(@ModelAttribute("tarea") Tarea tarea) {
        if (tarea.getId() != null) {
            // Editar tarea existente
            tareaServicio.editarTarea(tarea.getId(), tarea);
        } else {
            // Crear nueva tarea
            tareaServicio.crearTarea(tarea);
        }
        return "redirect:/tareas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable("id") Long id, Model modelo) {
        Tarea tarea = tareaServicio.obtenerTareaPorId(id);
        modelo.addAttribute("tarea", tarea);
        return "formulario-tarea";
    }


    @PostMapping("/editar/{id}")
    public String editarTarea(@PathVariable("id") Long id, @ModelAttribute("tarea") Tarea tarea) {
        // Obtener la tarea existente
        Tarea tareaExistente = tareaServicio.obtenerTareaPorId(id);

        if (tareaExistente != null) {
            // Actualizar los detalles de la tarea existente
            tareaExistente.setDescripcion(tarea.getDescripcion());
            tareaExistente.setFechaFinalizacion(tarea.getFechaFinalizacion());
            tareaExistente.setCompletado(tarea.isCompletado());

            // Guardar la tarea actualizada
            tareaServicio.editarTarea(id, tareaExistente);
        }
        return "redirect:/tareas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        tareaServicio.eliminarTarea(id);
        return "redirect:/tareas";
    }
}
