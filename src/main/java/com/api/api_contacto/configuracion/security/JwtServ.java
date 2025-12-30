package com.api.api_contacto.configuracion.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServ {

    @Value("${jwt.expiration}")
    private long JWT_EXPIRATION;

    @Value("${jwt.secret}")
    private String JWT_SECRET;


    // CREAR TOKEN
    public String getToken(UsuarioDetails usuario){
        return crearToken(usuario);
    }

    private String crearToken(UsuarioDetails usuario){

        Date fechaExpiracion = new Date(System.currentTimeMillis() + JWT_EXPIRATION);
        Date fechaCreacion = new Date(System.currentTimeMillis());

        return Jwts
            .builder()
            .setSubject(usuario.getUsername())
            .addClaims(getClaims(usuario))
            .setExpiration(fechaExpiracion)
            .setIssuedAt(fechaCreacion)
            .signWith(getKey())
            .compact();
    }


    private Map<String, Object> getClaims(UsuarioDetails usuario){
        Map<String, Object> claim = new HashMap<>();
        claim.put("id", usuario.getId());
        claim.put("roles",usuario.getAuthorities());

        return claim;
    }

    private Key getKey(){
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }


    public String username(String token){
        return getClaims(token).getSubject();
    }

    public boolean tokenValido(String token, UsuarioDetails usuario){
        final String username = getClaims(token).getSubject();

        return (username.equals(usuario.getUsername()) && !isExpirado(token));
    }


    private Claims getClaims(String token){
        return Jwts
            .parserBuilder()
            .setSigningKey(getKey()) // LLAVE PARA DESENTRIPTAR
            .build() 
            .parseClaimsJws(token) // PARSEAR CLAIMS
            .getBody();  // CLAIMS
    }

    private boolean isExpirado(String token){
        return (getClaims(token).getExpiration().before(new Date()));
    }
}