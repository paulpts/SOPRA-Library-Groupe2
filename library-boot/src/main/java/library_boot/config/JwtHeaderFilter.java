package library_boot.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library_boot.dao.IDAOUser;
import library_boot.model.User;

@Component
public class JwtHeaderFilter extends OncePerRequestFilter {
    @Autowired
    private IDAOUser daoUser;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null) {
            String token = header.substring(7); // On enlève "Bearer " pour garder que le jeton

            // On vérifie le jeton, et si tout est OK, on récupère l'utilisateur associé à ce jeton
            Optional<String> optLogin = JwtUtils.validateAndGetSubjet(token);

            if (optLogin.isPresent()) {
                User user = this.daoUser.findByLogin(optLogin.get()).orElseThrow();
                
                List<GrantedAuthority> autorities = new ArrayList<>();
                
                autorities.add(new SimpleGrantedAuthority("ROLE_USER"));

                // Créer, pour Spring Security, un nouvel User, avec le nom d'utilisateur, pas de mdp, et la liste des autorités
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(optLogin.get(), null,autorities);

                // Injecter notre nouvel authentication dans le contexte de Spring Security
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // Important pour chainer sur le filtre suivant
        filterChain.doFilter(request, response);
    }
}
