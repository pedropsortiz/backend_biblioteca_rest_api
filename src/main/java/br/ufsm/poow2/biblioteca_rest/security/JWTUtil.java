package br.ufsm.poow2.biblioteca_rest.security;

import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    private static final long TEMPO_VIDA = 3600000;
    private static final String CHAVE_ASSINATURA = "alexadriapoow2";

    public static String geraToken(Usuario usuario) {
        // Cria um mapa de claims vazio
        final Map<String, Object> claims = new HashMap<>();
        // Adiciona os claims "sub" e "permissão" ao mapa de claims
        claims.put("sub", usuario.getEmailUsuario());
        claims.put("permissao", usuario.getPermissaoUsuario());

        // Cria um builder de JWT
        return Jwts.builder()
                // Define os claims no builder
                .setClaims(claims)
                // Define a data de expiração do token como a data atual mais o tempo de vida do token
                .setExpiration(new Date(System.currentTimeMillis() + TEMPO_VIDA))
                // Assina o token usando a chave de assinatura e o algoritmo HS256
                .signWith(SignatureAlgorithm.HS256, CHAVE_ASSINATURA)
                // Gera o token como uma string compacta
                .compact();
    }


    public static boolean validaToken(String token) {
        try {
            Jws<Claims> jws = Jwts.parser()
                    .setSigningKey(CHAVE_ASSINATURA)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTokenExpirado(String token){
        // Parseia o token e recupera a data de expiração dos claims
        Date expirationDate = this.parseToken(token).getExpiration();
        // Verifica se a data de expiração do token é anterior à data atual
        return expirationDate.before(new Date());
    }

    public String getUsernameToken(String token){
        // Verifica se o token é nulo
        if (token != null){
            // Parseia o token e recupera o nome de usuário dos claims
            return this.parseToken(token).getSubject();
        }else{
            // Retorna null se o token for nulo
            return null;
        }
    }

    private Claims parseToken(String token){
        // Verifica se o token fornecido é nulo ou vazio
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token inválido - O token está vazio");
        }
        // Verifica se o token começa com "Bearer"
        if (!token.startsWith("Bearer")) {
            throw new IllegalArgumentException("Token inválido - O token não começa com Bearer");
        }
        // Usa a biblioteca Jwts para parsear o token e retornar os claims contidos nele
        return Jwts.parser()
                // Define a chave de assinatura para verificar a autenticidade do token
                .setSigningKey(CHAVE_ASSINATURA)
                // Parseia o token e recupera os claims
                .parseClaimsJws(token.replace("Bearer", ""))
                .getBody();
    }


}
