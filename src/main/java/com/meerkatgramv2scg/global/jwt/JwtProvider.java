package com.meerkatgramv2scg.global.jwt;

import com.meerkatgramv2scg.global.errors.custom.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import javax.crypto.SecretKey;
import java.util.Optional;

@Component
public class JwtProvider {
  private final SecretKey secretKey;
  private final JwtConfig jwtConfig;

  public JwtProvider(JwtConfig jwtConfig) {
    this.jwtConfig = jwtConfig;
    this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.secret()));
  }

  public Optional<String> extractAccessToken(ServerWebExchange exchange) {
    String bearerToken = exchange.getRequest().getHeaders().getFirst(jwtConfig.headerKey());

    if(bearerToken == null || !bearerToken.startsWith(jwtConfig.headerKey())) {
      return Optional.empty();
    }

    return Optional.of(bearerToken.substring(jwtConfig.scheme().length()).trim());
  }

  public Claims extractClaims(String token) {
    try {
        return Jwts.parser()
            .verifyWith(this.secretKey)
            .build()
            .parseClaimsJws(token)
            .getPayload()
            ;
    } catch(ExpiredJwtException e) {
        throw new InvalidTokenException("토큰 만료");
    } catch(UnsupportedJwtException e) {
        throw new InvalidTokenException("서명 위조 토큰");
    } catch(MalformedJwtException e) {
        throw new InvalidTokenException("올바르지 않은 형식의 토큰");
    } catch(JwtException | IllegalArgumentException e) {
        throw new InvalidTokenException("토큰 검증 실패");
    }
  }
}
