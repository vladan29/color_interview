package com.vladan.color_interview.utils

import android.util.Base64
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*

/**
 * Created by vladan on 8/27/2020
 */
object JWTUtils {
    fun createJWT(alg: String, typ: String, uuid: String, identity: String): String {
        val payloadMap: MutableMap<String, Any> = HashMap()
        val headerMap: MutableMap<String, Any> = HashMap()
        headerMap["alg"] = alg
        headerMap["typ"] = typ
        payloadMap["uid"] = uuid
        payloadMap["identity"] = identity
        val signatureAlgorithm = SignatureAlgorithm.HS256
        val encoded = Base64.encodeToString("\$SECRET$".toByteArray(), Base64.NO_WRAP)
        return Jwts.builder()
            .setHeader(headerMap)
            .setClaims(payloadMap)
            .signWith(signatureAlgorithm, encoded.toByteArray())
            .compact()
    }
}