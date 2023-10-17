package com.mayikt.edu.utils;

import cn.hutool.core.lang.UUID;

public class SaltUtils {
    /**
     * 生成盐值
     *
     * @return
     */
    public static String getSalt() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}