package br.ufsm.poow2.biblioteca_rest.security;

import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    public static final long TEMPO_VIDA = Duration.ofSeconds(40).toMillis();

    public String geraToken(Usuario usuario){

        final Map<String, Object> cliente = new HashMap<>();
        cliente.put("sub", usuario.getEmailUsuario());
        cliente.put("permissao", usuario.getPermissaoUsuario());

        return Jwts.builder()
                .setClaims(cliente)
                .setExpiration(new Date(System.currentTimeMillis()+TEMPO_VIDA))
                .signWith(SignatureAlgorithm.HS256, "alexadriapoow2")
                .compact();
    }

}
