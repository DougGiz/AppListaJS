package com.registro.usuarios.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
public class RegistroControlador {

	@Autowired
	private UsuarioServicio servicio;

	// Mapeo para la página de login
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}

	// Mapeo para la página de inicio después de iniciar sesión
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) {
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		return "inicio"; // Asegúrate de que este template sea el correcto
	}
}
