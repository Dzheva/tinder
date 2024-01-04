package application.filters;

import application.constants.Endpoint;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public final class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) request).getRequestURI().toLowerCase();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        // TODO: 👉 Add sessions persistence
        if (session != null || requestURI.equals(Endpoint.LOGIN) || requestURI.contains("/static")) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(Endpoint.LOGIN);
        }
    }
}