package com.atlavik.cartapi.filter;

import com.atlavik.cartapi.model.dto.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiKeyRequestFilter implements Filter {

    @Value("${api-key}")
    String apiKey;

    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        if (!httpServletRequest.getRequestURI().startsWith("/api/carts")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (apiKey.equals(httpServletRequest.getParameter("apiKey"))) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        ObjectMapper om =new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        String json = om.writeValueAsString(new ApiError("API Key cannot Empty",  HttpStatus.FORBIDDEN.value()));

        httpServletResponse.reset();
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setContentLengthLong(json.length());
        httpServletResponse.getWriter().write(json);
    }
}
