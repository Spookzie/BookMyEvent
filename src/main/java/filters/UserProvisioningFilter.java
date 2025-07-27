package filters;

import domain.entities.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;
import repositories.UserRepository;

import java.io.IOException;
import java.util.UUID;


/*
* Creating new user in our database when they first log in
* This ensures that every authenticated user has a corresponding "User" in the database
****************************/
@Component
@RequiredArgsConstructor
public class UserProvisioningFilter extends OncePerRequestFilter
{
    private final UserRepository userRepo;


    @Transactional
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        // Get the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Keycloak issues a JWT everytime a user logs in
        if(authentication != null && authentication.isAuthenticated()
            && authentication.getPrincipal() instanceof Jwt jwt)
        {
            // Extract the user ID from the JWT subject
            UUID keycloakId = UUID.fromString(jwt.getSubject());

            if(!this.userRepo.existsById(keycloakId))
            {
                User user = new User();
                user.setId(keycloakId);
                user.setName(jwt.getClaimAsString("preferred_username"));
                user.setEmail(jwt.getClaimAsString("email"));

                this.userRepo.save(user);
            }
        }

        filterChain.doFilter(request, response);
    }
}