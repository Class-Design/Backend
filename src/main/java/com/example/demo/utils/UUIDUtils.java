package com.example.demo.utils;

import java.util.UUID;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
public class UUIDUtils {
    public static final String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
