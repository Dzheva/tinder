package application.filters;

import application.Utils.CookieManager;
import application.constants.Endpoint;
import application.entities.SessionData;
import application.models.User;
import application.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

public final class GlobalFilter implements Filter {
    private final UserService userService;

    public GlobalFilter() {
        userService = new UserService();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) request).getRequestURI().toLowerCase();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        if (session != null || requestURI.equals(Endpoint.LOGIN) || requestURI.contains(Endpoint.STATIC)) {
            chain.doFilter(request, response);
            return;
        }

        Map<String, String> cookies = CookieManager.getCookies(httpRequest);
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        User user = getUserFromCookies(cookies);

        if (user != null) {
            HttpSession newSession = httpRequest.getSession();
            SessionData data = new SessionData(user.id, userService.getCarouselUsers(user.id), 0);
            newSession.setAttribute("data", data);
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(Endpoint.LOGIN);
        }
    }

    private User getUserFromCookies(Map<String, String> cookies) {
        if (cookies.containsKey("username") && cookies.containsKey("password")) {
            return userService.getUser(cookies.get("username"), cookies.get("password"));
        }
        return null;
    }
}