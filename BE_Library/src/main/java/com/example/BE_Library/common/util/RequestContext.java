package com.example.BE_Library.common.util;

import com.example.BE_Library.common.dto.JwtData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class RequestContext {
    public static String getUserId() {
        JwtData jwtData = getJwtData();
        if (jwtData == null) {
            return null;
        }
        return jwtData.getUserId();
    }

    private static JwtData getJwtData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null
                || authentication.getPrincipal() == null
                || authentication.getPrincipal().toString().equals("anonymousUser")) {
            return null;
        }
        return (JwtData) authentication.getPrincipal();
    }
}
