package com.baskapp.baskappsocial.application.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@Component
public class BaskappAuthUtil {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private int EXPIRATION_TIME;

    @Value("${jwt.algorithm}")
    private String ALGORITHM;

    public String generateJwt(String userId) {
        try {
            String headerJson = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
            String headerBase64 = Base64.getUrlEncoder().withoutPadding().encodeToString(headerJson.getBytes());

            long expirationTime = System.currentTimeMillis() / 1000 + EXPIRATION_TIME;
            String payloadJson = "{\"sub\":\"" + userId + "\",\"exp\":" + expirationTime + "}";
            String payloadBase64 = Base64.getUrlEncoder().withoutPadding().encodeToString(payloadJson.getBytes());

            String signature = generateHmac(headerBase64 + "." + payloadBase64, SECRET_KEY);

            return headerBase64 + "." + payloadBase64 + "." + signature;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar JWT", e);
        }
    }

    public Map<String, Object> validateJwt(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) throw new RuntimeException("Token inválido");

            String headerBase64 = parts[0];
            String payloadBase64 = parts[1];
            String signatureReceived = parts[2];

            String expectedSignature = this.generateHmac(headerBase64 + "." + payloadBase64, SECRET_KEY);
            if (!expectedSignature.equals(signatureReceived)) {
                throw new RuntimeException("Assinatura JWT inválida");
            }

            String payloadJson = new String(Base64.getUrlDecoder().decode(payloadBase64));
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> payload = mapper.readValue(payloadJson, Map.class);

            long expiration = ((Number) payload.get("exp")).longValue();
            if (System.currentTimeMillis() / 1000 > expiration) {
                throw new RuntimeException("Token expirado");
            }

            return payload;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao validar JWT", e);
        }
    }

    public String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }

    private String generateHmac(String data, String secret) throws Exception {
        Key key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(key);
        byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
    }
}
