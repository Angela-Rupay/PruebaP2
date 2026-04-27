package institucion.com.colegio.controllers;

import institucion.com.colegio.dto.AsignaturaDTO;
import institucion.com.colegio.dto.UsuarioAdminDTO;
import institucion.com.colegio.repository.RolRepository;
import institucion.com.colegio.repository.UsuarioRepository;
import institucion.com.colegio.services.RectorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rector")
public class RectorController {

    private final RectorService rectorService;
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;

    public RectorController(RectorService rectorService,
                            RolRepository rolRepository,
                            UsuarioRepository usuarioRepository) {
        this.rectorService = rectorService;
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Panel principal del rector
    @GetMapping("")
    public String panelRector() {
        return "rector";
    }

    // Mostrar formulario para crear usuario
    @GetMapping("/crear-usuario")
    public String mostrarFormularioUsuario(Model model) {
        model.addAttribute("usuarioAdminDTO", new UsuarioAdminDTO());
        model.addAttribute("roles", rolRepository.findAll());
        return "crear-usuario";
    }

    // Guardar usuario creado
    @PostMapping("/crear-usuario")
    public String crearUsuario(@ModelAttribute UsuarioAdminDTO dto) {
        rectorService.crearUsuario(dto);
        return "redirect:/rector";
    }

    // Mostrar formulario para crear asignatura
    @GetMapping("/crear-asignatura")
    public String mostrarFormularioAsignatura(Model model) {
        model.addAttribute("asignaturaDTO", new AsignaturaDTO());

        // Solo usuarios con rol DOCENTE
        model.addAttribute("docentes",
                usuarioRepository.findByRol_Tipo("DOCENTE"));

        return "crear-asignatura";
    }

    // Guardar asignatura creada
    @PostMapping("/crear-asignatura")
    public String crearAsignatura(@ModelAttribute AsignaturaDTO dto) {
        rectorService.crearAsignatura(dto);
        return "redirect:/rector";
    }
}
