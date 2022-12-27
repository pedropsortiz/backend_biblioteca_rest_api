package br.ufsm.poow2.biblioteca_rest.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltroAutenticacao extends OncePerRequestFilter {

    @Autowired private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();

        // Verifica se a URL é a de login
        if (url.contains("/login")) {
            // Obtém o token de autenticação da requisição
            String token = request.getHeader("Authorization");
            // Obtém o nome de usuário do token
            String username = new JWTUtil().getUsernameToken(token);

            // Verifica se o nome de usuário foi obtido e se o contexto de segurança atual não possui autenticação
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Carrega os detalhes do usuário pelo nome de usuário
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                // Verifica se o token não está expirado
                if (!new JWTUtil().isTokenExpirado(token)) {
                    // Cria um novo token de autenticação com base nos detalhes do usuário
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Atribui o token de autenticação ao contexto de segurança atual
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        // Executa o restante da cadeia de filtros
        filterChain.doFilter(request, response);
    }

}
