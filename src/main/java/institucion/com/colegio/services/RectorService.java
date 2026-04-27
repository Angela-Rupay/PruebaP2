package institucion.com.colegio.services;

import institucion.com.colegio.dto.AsignaturaDTO;
import institucion.com.colegio.dto.UsuarioAdminDTO;
import institucion.com.colegio.model.Asignatura;
import institucion.com.colegio.model.Rol;
import institucion.com.colegio.model.Usuario;
import institucion.com.colegio.repository.AsignaturaRepository;
import institucion.com.colegio.repository.RolRepository;
import institucion.com.colegio.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

    @Service
    public class RectorService {

        private final UsuarioRepository usuarioRepository;
        private final RolRepository rolRepository;
        private final AsignaturaRepository asignaturaRepository;
        private final PasswordEncoder passwordEncoder;

        public RectorService(UsuarioRepository usuarioRepository,
                             RolRepository rolRepository,
                             AsignaturaRepository asignaturaRepository,
                             PasswordEncoder passwordEncoder) {
            this.usuarioRepository = usuarioRepository;
            this.rolRepository = rolRepository;
            this.asignaturaRepository = asignaturaRepository;
            this.passwordEncoder = passwordEncoder;
        }

        public void crearUsuario(UsuarioAdminDTO dto) {

            Rol rol = rolRepository.findById(dto.getRolId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

            Usuario usuario = new Usuario();
            usuario.setNombre(dto.getNombre());
            usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
            usuario.setRol(rol);

            usuarioRepository.save(usuario);
        }

        public void crearAsignatura(AsignaturaDTO dto) {

            Usuario docente = usuarioRepository.findById(dto.getDocenteId())
                    .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

            Asignatura asignatura = new Asignatura();
            asignatura.setNombre(dto.getNombre());
            asignatura.setHoraInicio(dto.getHoraInicio());
            asignatura.setHoraFin(dto.getHoraFin());
            asignatura.setSalon(dto.getSalon());
            asignatura.setDocente(docente);

            asignaturaRepository.save(asignatura);
        }
    }

