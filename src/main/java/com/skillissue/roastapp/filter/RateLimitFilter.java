package com.skillissue.roastapp.filter;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RateLimitFilter implements Filter {

    private final RateLimiterRegistry rateLimiterRegistry;

    public RateLimitFilter(RateLimiterRegistry rateLimiterRegistry) {
        this.rateLimiterRegistry = rateLimiterRegistry;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String clientIp = httpRequest.getRemoteAddr();

        RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter(clientIp);

        if (rateLimiter.acquirePermission()) {
            chain.doFilter(request, response);
        } else {
            httpResponse.setStatus(429); // 429 Too Many Requests
            httpResponse.getWriter().write("Too many requests. Please try again later.");
        }
    }
}