package institucion.com.colegio.controllers;

import institucion.com.colegio.dto.UsuarioRegistroDTO;
import institucion.com.colegio.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String mostrarLogin() {
        return "index";
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute UsuarioRegistroDTO dto) {
        usuarioService.registrarEstudiante(dto);
        return "redirect:/";
    }

    @GetMapping("/estudiante")
    public String vistaEstudiante() {
        return "estudiante";
    }

    @GetMapping("/docente")
    public String vistaDocente() {
        return "docente";
    }

    @GetMapping("/rector")
    public String vistaRector() {
        return "rector";
    }
}