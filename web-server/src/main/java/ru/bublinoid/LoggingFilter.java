package ru.bublinoid;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Инициализация, если требуется
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("Request URL: " + httpRequest.getRequestURL());
        System.out.println("Request Method: " + httpRequest.getMethod());
        System.out.println("Request Parameters: " + httpRequest.getParameterMap().toString());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Очистка, если требуется
    }
}
