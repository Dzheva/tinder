package application.servlets;

import application.Utils.CookieManager;
import application.constants.Endpoint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class Logout extends BaseServlet {
    public Logout() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handleLogout(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handleLogout(request, response);
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession(false).invalidate();
        CookieManager.removeCookies(request, response);
        response.sendRedirect(Endpoint.LOGIN);
    }
}
