package institucion.com.colegio.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        var authorities = authentication.getAuthorities();

        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ESTUDIANTE"))) {
            response.sendRedirect("/estudiante");
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_DOCENTE"))) {
            response.sendRedirect("/docente");
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_RECTOR"))) {
            response.sendRedirect("/rector");
        } else {
            response.sendRedirect("/");
        }
    }
}