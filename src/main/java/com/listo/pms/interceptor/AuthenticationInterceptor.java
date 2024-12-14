package com.listo.pms.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader(AUTH_HEADER);

        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            logger.error("Missing or invalid Authorization header.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Missing or invalid Authorization token.");
            return false;
        }

        String token = authHeader.substring(BEARER_PREFIX.length());

        // Validate the token
        if (!isValidToken(token)) {
            logger.error("Invalid token: {}", token);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid token.");
            return false;
        }

        return true;
    }

    // Dummy token validation
    private boolean isValidToken(String token) {
        // Validate the token (check its signature, expiration, etc.)
        // For simplicity, we return true if the token is "valid-token"
        return token.equals("valid-token");
    }
}
