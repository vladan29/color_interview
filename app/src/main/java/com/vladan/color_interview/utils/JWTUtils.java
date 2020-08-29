package com.vladan.color_interview.utils;

import android.util.Base64;

import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created by vladan on 8/27/2020
 */
public class JWTUtils {

    public static String createJWT(String alg, String typ, String uuid, String identity) {
        Map<String, Object> payloadMap = new HashMap<>();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", alg);
        headerMap.put("typ", typ);
        payloadMap.put("uid", uuid);
        payloadMap.put("identity", identity);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        String encoded = Base64.encodeToString("$SECRET$".getBytes(), Base64.NO_WRAP);
        return Jwts.builder()
                .setHeader(headerMap)
                .setClaims(payloadMap)
                .signWith(signatureAlgorithm, encoded.getBytes())
                .compact();
    }
}
