package com.greenwich.comp1640.security.jwt.util;

import com.greenwich.comp1640.config.jwt.JWTVerifierConfigProperties;
import com.greenwich.comp1640.exception.CustomExceptions;
import com.greenwich.comp1640.util.DateUtil;
import com.greenwich.comp1640.util.signature.ECDSA;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class JWTUtil {
    @Autowired
    ECDSA ecdsa;

    @Autowired
    JWTVerifierConfigProperties jwtVerifierConfigProperties;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(token);

            return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            PublicKey publicKey = ecdsa.convertPubKeyStrToPubKey(jwtVerifierConfigProperties.getPublicKey());

            return (Claims) Jwts
                    .parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parse(token)
                    .getBody();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            throw new CustomExceptions.InternalException("Server error");
        } catch (ExpiredJwtException ex) {
            throw ex;
        }
    }

    public String generateToken(UserDetails userDetails) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, userDetails.getUsername());
    }

    public String createToken(Map<String, Object> claims, String subject) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(DateUtil.now())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(ecdsa.convertPrivKeyStrToKey(jwtVerifierConfigProperties.getPrivateKey()), SignatureAlgorithm.ES256)
                .compact();
    }

    public Map<String, Object> getMapFromJwtClaims(Claims claims) {
        Map<String, Object> map = new HashMap<>();

        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }

        return map;
    }
}
