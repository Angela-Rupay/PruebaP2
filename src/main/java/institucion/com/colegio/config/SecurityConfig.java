package institucion.com.colegio.config;

import institucion.com.colegio.services.UsuarioDetailsService;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UsuarioDetailsService usuarioDetailsService;
    private final CustomLoginSuccessHandler successHandler;

    public SecurityConfig(UsuarioDetailsService usuarioDetailsService,
                          CustomLoginSuccessHandler successHandler) {
        this.usuarioDetailsService = usuarioDetailsService;
        this.successHandler = successHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder delegate = new BCryptPasswordEncoder();

        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return delegate.encode(rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                if (encodedPassword != null && encodedPassword.startsWith("$2")) {
                    return delegate.matches(rawPassword, encodedPassword);
                }

                return encodedPassword != null && encodedPassword.contentEquals(rawPassword);
            }
        };
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider(usuarioDetailsService);

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/registro", "/css/**").permitAll()
                        .requestMatchers("/estudiante").hasRole("ESTUDIANTE")
                        .requestMatchers("/docente").hasRole("DOCENTE")
                        .requestMatchers("/rector/**").hasRole("RECTOR")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/")
                        .loginProcessingUrl("/login")
                        // Esta redirección por rol solo ocurre si el formulario supera la validación CSRF y la autenticación.
                        .successHandler(successHandler)
                        .failureUrl("/?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }
}
