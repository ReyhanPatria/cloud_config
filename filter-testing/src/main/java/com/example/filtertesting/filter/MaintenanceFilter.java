package com.example.filtertesting.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.filtertesting.config.MaintenanceConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MaintenanceFilter extends OncePerRequestFilter {
    @Autowired
    private MaintenanceConfig maintenanceConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();

        if(maintenanceConfig.getUrls().contains(path)) {
            response.sendRedirect("/maintenance");
        }
        
        filterChain.doFilter(request, response);
    }
}