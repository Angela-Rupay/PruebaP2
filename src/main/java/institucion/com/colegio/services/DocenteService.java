package institucion.com.colegio.services;

import institucion.com.colegio.model.Asignatura;
import institucion.com.colegio.model.Usuario;
import institucion.com.colegio.repository.AsignaturaRepository;
import institucion.com.colegio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService {

    private final AsignaturaRepository asignaturaRepository;
    private final UsuarioRepository usuarioRepository;

    public DocenteService(AsignaturaRepository asignaturaRepository,
                          UsuarioRepository usuarioRepository) {
        this.asignaturaRepository = asignaturaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Asignatura> obtenerAsignaturasDocente(String nombreUsuario) {

        Usuario docente = usuarioRepository.findByNombre(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

        return asignaturaRepository.findByDocente_IdUsuario(docente.getIdUsuario());
    }
}
