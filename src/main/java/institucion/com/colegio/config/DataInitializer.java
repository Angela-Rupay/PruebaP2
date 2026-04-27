package institucion.com.colegio.config;

import institucion.com.colegio.model.Rol;
import institucion.com.colegio.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RolRepository rolRepository) {
        return args -> {

            if (rolRepository.findByTipo("DOCENTE").isEmpty()) {
                rolRepository.save(new Rol(null, "DOCENTE", null));
            }

            if (rolRepository.findByTipo("ESTUDIANTE").isEmpty()) {
                rolRepository.save(new Rol(null, "ESTUDIANTE", null));
            }

            if (rolRepository.findByTipo("RECTOR").isEmpty()) {
                rolRepository.save(new Rol(null, "RECTOR", null));
            }
        };
    }
}