package com.example.filtertesting.filter;

import com.example.filtertesting.config.MaintenanceConfig;
import com.example.filtertesting.config.MaintenanceEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MaintenanceFilter extends OncePerRequestFilter {
    @Autowired
    private MaintenanceConfig maintenanceConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();

        for(MaintenanceEndpoint me: maintenanceConfig.getEndpoints()) {
            
            if(me.getUrls().contains(path)) {
                LocalDateTime current = LocalDateTime.now();
                if(current.isAfter(me.getStart()) && current.isBefore(me.getEnd())) {
                    request.setAttribute("maintenance-code", me.getCode());
                    request.setAttribute("maintenance-message", me.getMessage());
                    request.getRequestDispatcher("/maintenance").forward(request, response);
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}