package com.easydmarc.socialnetworkmvp.util;

public class TokenUtil {

    private TokenUtil() {
    }

    public static String extractTokenFromHeader(String authHeader) {
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        jwt = authHeader.substring(7);

        return jwt;
    }
}
