/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.service;

import com.main.servicoFinal.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    
    public SecretKey getKeySign(){
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String gerarToken(User user){

    if(user == null ||
       user.getId() == null ||
       user.getNome() == null ||
       user.getEmail() == null){

        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Dados do usuário inválidos"
        );
    }

    return Jwts.builder()
            .subject(user.getEmail())
            .claim("id", user.getId())
            .claim("nome", user.getNome())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 3000000))
            .signWith(this.getKeySign())
            .compact();
}
    public boolean validarToken(String token) {
        try {
            // Cria um parser JWT com a chave secreta para validação
            Jwts.parser()
                    .setSigningKey(getKeySign())
                    .build()
                    // Analisa e valida o token (lança exceção se inválido ou expirado)
                    .parseClaimsJws(token);
            // Se chegou aqui, o token é válido
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            
             throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token expirado ou invalido");
        }
    }
    
    public User extrairClaims(String token){

    Claims claim = Jwts.parser()
            .verifyWith(this.getKeySign())
            .build()
            .parseSignedClaims(token)
            .getPayload();

    User user = new User();

    user.setId(claim.get("id", Long.class));
    user.setNome(claim.get("nome", String.class));
    user.setEmail(claim.getSubject());

    return user;
}
    
    

}
