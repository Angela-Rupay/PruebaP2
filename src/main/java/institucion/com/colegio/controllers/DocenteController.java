package institucion.com.colegio.controllers;

import institucion.com.colegio.services.DocenteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocenteController {

    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping("/docente")
    public String vistaDocente(Authentication authentication, Model model) {

        String nombreUsuario = authentication.getName();

        model.addAttribute("asignaturas",
                docenteService.obtenerAsignaturasDocente(nombreUsuario));

        return "docente";
    }
}
