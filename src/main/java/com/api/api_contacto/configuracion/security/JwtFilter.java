package com.api.api_contacto.configuracion.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private JwtServ jwtServ;

    @Autowired
    private UsuarioDetailsServ usuarioDetailsServ;

    @Override
    protected void doFilterInternal(@org.springframework.lang.NonNull HttpServletRequest request, @org.springframework.lang.NonNull HttpServletResponse response, @org.springframework.lang.NonNull FilterChain filterChain) throws ServletException, IOException {

        String nameEx="JWT_ERROR";
        try {
            
            final String token = getTokenHeader(request);

            if(token == null){ 
                request.setAttribute(nameEx,"empty"); 
            }
            else{
                final String username = jwtServ.username(token);

                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UsuarioDetails usuario = (UsuarioDetails) usuarioDetailsServ.loadUserByUsername(username);

                    if(jwtServ.tokenValido(token, usuario)){
                        Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } // It´s never too late to learn programming

        } catch(ExpiredJwtException ex){
            request.setAttribute(nameEx,"expired");
        } catch (IllegalArgumentException ex){
            request.setAttribute(nameEx,"empty");
        } catch (SecurityException ex){
            request.setAttribute(nameEx,"invalid-signature");
        }catch (MalformedJwtException ex){
            request.setAttribute(nameEx,"malformed");
        }finally{
            filterChain.doFilter(request, response);
        }
    }

    private String getTokenHeader(HttpServletRequest request){
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(header) && header.startsWith("Bearer ")){
            return header.substring(7);
        }

        return null;
    }
}