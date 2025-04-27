package com.jihaddmz.moviefinderbackendjava.components;

import com.jihaddmz.moviefinderbackendjava.services.JwtService;
import com.jihaddmz.moviefinderbackendjava.services.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MyUserDetailsService userDetailsService;

    public JWTAuthenticationFilter(JwtService jwtService, MyUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // user hasn't sent the token with the request
            filterChain.doFilter(request, response);
            return;
        }

        final String jwtToken = authHeader.split(" ")[1];

        if (jwtToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final String userEmail = jwtService.extractUserIdFromToken(jwtToken);

        if (userEmail == null || SecurityContextHolder.getContext().getAuthentication() != null) { // no user is associated with this token, or the user is
            // already authenticated so no need for re-authentication
            filterChain.doFilter(request, response);
            return;
        }

        if (jwtService.isTokenValid(jwtToken)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // attaching extra info the authToken, like Ip address, session id, etc...
            // for auditing and logging purposes

            SecurityContextHolder.getContext().setAuthentication(authToken); // making the current user authenticated
        }

        filterChain.doFilter(request, response);
    }
}
