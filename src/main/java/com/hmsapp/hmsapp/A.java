package com.hmsapp.hmsapp;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class A {
    public static void main(String[] args) {
        String enocdedPwd = BCrypt.hashpw("testing", BCrypt.gensalt(10));
        System.out.println(enocdedPwd);
    }
}
