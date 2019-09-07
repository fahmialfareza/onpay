package com.dinokeylas.onpay.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encript {

    private String input;
    private String result = "";

    public Encript(String input) {
        this.input = input;
    }

    public String getEcripted() {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(this.input.getBytes());
            byte[] messageDigest = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) hexString.append(Integer.toHexString(0xFF & b));
            result = hexString.toString();
            return result;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }
}
