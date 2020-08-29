package com.vladan.color_interview.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vladan on 8/29/2020
 */
public class AppConstants {

    //Prefs keys
    public static String PREFS_NAME = "color";
    public static String JWT_TOKEN_KEY = "JWT";
    public static String IDENTITY = "user";
    public static String ALGORITHM = "HS256";
    public static String TYPE = "JWT";

    public static String checkOrCreateJWT(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String JWTToken = sharedPreferences.getString(JWT_TOKEN_KEY, "");
        assert JWTToken != null;
        if (JWTToken.equals("")) {
            String uuid = UuidGenerator.generateUuid();
            String generatedJWTToken = JWTUtils.createJWT(ALGORITHM, TYPE, uuid, IDENTITY);
            sharedPreferences.edit().putString(JWT_TOKEN_KEY, generatedJWTToken).apply();
            JWTToken = generatedJWTToken;
        }
        return JWTToken;
    }
}
