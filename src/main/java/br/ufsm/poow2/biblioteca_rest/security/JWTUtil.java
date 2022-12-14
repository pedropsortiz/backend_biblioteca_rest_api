package br.ufsm.poow2.biblioteca_rest.security;

import br.ufsm.poow2.biblioteca_rest.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    public static final long TEMPO_VIDA = Duration.ofDays(1).toMillis();

    public String geraToken(User user){

        final Map<String, Object> cliente = new HashMap<>();
        cliente.put("sub", user.getEmail());
        cliente.put("permissao", user.getPermission());

        return Jwts.builder()
                .setClaims(cliente)
                .setExpiration(new Date(System.currentTimeMillis()+TEMPO_VIDA))
                .signWith(SignatureAlgorithm.HS256, "alexadriapoow2")
                .compact();
    }

    public boolean isTokenExpirado(String token){
        return this.parseToken(token).getExpiration().before(new Date());
    }

    public String getUsernameToken(String token){
        if (token != null){
            return this.parseToken(token).getSubject();
        }else{
            return null;
        }
    }

    private Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey("alexadriapoow2")
                .parseClaimsJws(token.replace("Bearer", ""))
                .getBody();
    }

}
