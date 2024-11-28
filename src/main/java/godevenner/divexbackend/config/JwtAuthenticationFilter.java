package godevenner.divexbackend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {//means every request needs to be authenticated, the session should not be stored on server ie, stateless

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        //this header contains the jwt token
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        //Check JWT token, if there is no token do an early return.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //extracts the jwt token from the authHeader, beginning at index 7, since it starts with "Bearer ...jwt token"
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);

        //check if user is already authenticated if not get the userdetials from the db
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //fetches the userDetails from the db.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            //checks if the token is valid if it is valid then we create a new AuthenticationToken
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)); // the request contains username and password

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }


        }
        //this is the end of this filter, so we need to execute the next filter inline:
        filterChain.doFilter(request, response);
    }
}