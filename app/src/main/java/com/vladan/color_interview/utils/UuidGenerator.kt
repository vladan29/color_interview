package com.vladan.color_interview.utils;

import java.util.UUID;

/**
 * Created by vladan on 8/27/2020
 */
public class UuidGenerator {

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
