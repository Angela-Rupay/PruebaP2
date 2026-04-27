package institucion.com.colegio.dto;

import institucion.com.colegio.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class AsignaturaDTO {
    private Long idAsignatura;
    private String nombre;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String salon;
    private Long docenteId;

}
