package institucion.com.colegio.services;

import institucion.com.colegio.model.Usuario;
import institucion.com.colegio.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

    @Service
    public class UsuarioDetailsService implements UserDetailsService {

        private final UsuarioRepository usuarioRepository;

        public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
            this.usuarioRepository = usuarioRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            Usuario usuario = usuarioRepository.findByNombre(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            return new User(
                    usuario.getNombre(),
                    usuario.getPassword(),
                    Collections.singletonList(
                            new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getTipo())
                    )
            );
        }
    }

