package application.servlets;

import application.Utils.CookieManager;
import application.constants.Endpoint;
import application.constants.TemplateName;
import application.entities.SessionData;
import application.models.User;
import application.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.List;

public final class Login extends BaseServlet {
    private final UserService service;

    public Login() {
        super(TemplateName.LOGIN);
        this.service = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = service.getUser(username, DigestUtils.sha256Hex(password));
        if (user != null) {
            List<User> carouselUsers = service.getCarouselUsers(user.id);
            session.setAttribute("data", new SessionData(user.id, carouselUsers, 0));
            CookieManager.setCookies(response, new Cookie("username", user.username),
                    new Cookie("password", user.password));
            response.sendRedirect(Endpoint.USERS);
        } else {
            response.sendRedirect(Endpoint.LOGIN);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        renderTemplate(response);
    }
}