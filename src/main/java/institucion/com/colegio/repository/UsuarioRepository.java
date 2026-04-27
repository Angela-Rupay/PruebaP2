package institucion.com.colegio.repository;

import institucion.com.colegio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

    public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
        Optional<Usuario> findByNombre(String nombre);
        List<Usuario> findByRol_Tipo(String tipo);
    }

