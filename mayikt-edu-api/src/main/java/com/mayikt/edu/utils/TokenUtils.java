package com.mayikt.edu.utils;

import cn.hutool.core.lang.UUID;

public class TokenUtils {

    public static String getToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}