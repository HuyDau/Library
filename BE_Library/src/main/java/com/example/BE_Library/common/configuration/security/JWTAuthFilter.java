package com.example.BE_Library.common.configuration.security;

import com.example.BE_Library.auth.service.CustomUserDetailsService;
import com.example.BE_Library.auth.utils.JWTUtils;
import com.example.BE_Library.common.dto.JwtData;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmailFromToken;
        final String userIdFromToken;     // Thay đổi kiểu dữ liệu thành String
        final String roleFromToken;       // Thay đổi kiểu dữ liệu thành String

        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7);

        userEmailFromToken = jwtUtils.extractUsername(jwtToken);
        userIdFromToken = jwtUtils.extractUserId(jwtToken);   // Gọi phương thức extractUserId mới
        roleFromToken = jwtUtils.extractRole(jwtToken);     // Gọi phương thức extractRole mới (đã đổi tên)

        if (userEmailFromToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetailsFromDb = customUserDetailsService.loadUserByUsername(userEmailFromToken);

            if (jwtUtils.isValidToken(jwtToken, userDetailsFromDb)) {
                // *** THAY ĐỔI QUAN TRỌNG: Tạo đối tượng JwtData với String role ***
                JwtData jwtData = new JwtData(userIdFromToken, userEmailFromToken, roleFromToken);

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                // Chuyển đổi String role thành List<GrantedAuthority>
                // Nếu roleFromToken là null hoặc rỗng, trả về một danh sách trống
                List<SimpleGrantedAuthority> authorities;
                if (roleFromToken != null && !roleFromToken.isEmpty()) {
                    authorities = Arrays.asList(new SimpleGrantedAuthority(roleFromToken));
                } else {
                    authorities = Collections.emptyList();
                }

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        jwtData, // PRINCIPAL: JwtData với String role
                        null,
                        authorities // Authorities cho Spring Security
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(authToken);
                SecurityContextHolder.setContext(securityContext);
            } else {
                System.out.println("JWT không hợp lệ hoặc chi tiết người dùng không khớp cho: " + userEmailFromToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
