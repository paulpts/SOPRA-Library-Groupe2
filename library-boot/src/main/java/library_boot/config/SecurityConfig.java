package library_boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {
	
	  @Bean // On bypass la config auto-configuration
	    SecurityFilterChain filterChain(HttpSecurity http, JwtHeaderFilter jwtFilter) throws Exception {
		  
		  http.authorizeHttpRequests(auth -> {
	            // On autorise les ressources externes (JSP, CSS, JS, IMG)
	            auth.requestMatchers("/*.css", "/assets/**").permitAll();

	            // On autorise tout le monde sur connexion
	            auth.requestMatchers(HttpMethod.POST, "/api/auth").permitAll();

	            // Sinon, accès restreint aux utilisateurs authentifiés
	            auth.requestMatchers("/**").authenticated();
	        });
		  
		  
		    // Désactiver la protection CSRF
	        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
	        
	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	        
	        return http.build();
		  
	
	  }
	
	   @Bean
	    PasswordEncoder passwordEncoder() {
	    	System.out.println(new BCryptPasswordEncoder().encode("123456"));
	        return new BCryptPasswordEncoder();
	    }
	   
	    // Permet d'injecter dans le contexte de Spring l'AuthenticationManager actuellement utilisé par Spring Security
	    @Bean
	    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	
}
