package institucion.com.colegio.services;

import institucion.com.colegio.dto.UsuarioRegistroDTO;
import institucion.com.colegio.model.Rol;
import institucion.com.colegio.model.Usuario;
import institucion.com.colegio.repository.RolRepository;
import institucion.com.colegio.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registrarEstudiante(UsuarioRegistroDTO dto) {

        Rol rolEstudiante = rolRepository.findByTipo("ESTUDIANTE")
                .orElseThrow(() -> new RuntimeException("Rol ESTUDIANTE no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(rolEstudiante);

        usuarioRepository.save(usuario);
    }
}