package com.kiljaeden.salarysys.common;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @Author: Kil'jaeden
 * @Email: pure3306@163.com
 * @Date: 2023/6/28 19:02
 * @Description:
 */
public class MD5Util {
    public static String GetPasswordByMD5(String password){
        return new String(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));
    }
}