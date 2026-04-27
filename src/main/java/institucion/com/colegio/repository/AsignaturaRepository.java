package institucion.com.colegio.repository;

import institucion.com.colegio.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
    List<Asignatura> findByDocente_IdUsuario(Long idUsuario);
}
