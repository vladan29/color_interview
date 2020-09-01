package com.vladan.color_interview.utils

import android.content.Context

/**
 * Created by vladan on 8/29/2020
 */
class AppConstants {
    companion object{
    //Prefs keys
    const val PREFS_NAME = "color"
    const val JWT_TOKEN_KEY = "JWT"
    const val IDENTITY = "user"
    const val ALGORITHM = "HS256"
    const val TYPE = "JWT"
    const val KEY_PERSON_ID = "person_id"

    fun checkOrCreateJWT(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        var JWTToken = sharedPreferences.getString(JWT_TOKEN_KEY, "")!!
        if (JWTToken == "") {
            val uuid = UuidGenerator.generateUuid()
            val generatedJWTToken = JWTUtils.createJWT(ALGORITHM, TYPE, uuid, IDENTITY)
            sharedPreferences.edit().putString(JWT_TOKEN_KEY, generatedJWTToken).apply()
            JWTToken = generatedJWTToken
        }
        return JWTToken
    }
}}