package com.example.Complexo.infra.security;

import com.example.Complexo.model.UserDetails.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    //private static final List<String> PUBLIC_URLS = List.of(
   //     "/auth/login",
   //     "/auth/register/studio",
// "/auth/register/cliente",
 //       "/artistas/searchByName"
// );

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws ServletException, IOException {

        String path   = req.getServletPath();
        String method = req.getMethod();

        // Se for rota pública, libera
       // if ( (method.equalsIgnoreCase("POST") && PUBLIC_URLS.contains(path))
       // || (method.equalsIgnoreCase("GET") && path.equals("/artistas/searchByName"))
       // ) {
       //     chain.doFilter(req, res);
       //     return;
       // }

        // Validação JWT
        String token = recoverToken(req);
        if (token != null) {
            String username = tokenService.validateToken(token);
            if (username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                var auth = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(req, res);
    }

    private String recoverToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.substring(7);
    }
}