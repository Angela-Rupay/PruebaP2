package institucion.com.colegio.dto;

import institucion.com.colegio.model.Rol;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UsuarioAdminDTO {
        private String nombre;
        private String password;
        private Long rolId;
}

