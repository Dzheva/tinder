package application.servlets;

import application.constants.Endpoint;
import application.constants.TemplateName;
import application.entities.SessionData;
import application.models.User;
import application.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

public class Login extends BaseServlet {
    public Login() {
        super(TemplateName.LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserService service = new UserService();
        User user = service.getUserById(1);
        session.setAttribute(
                "sessionData", new SessionData(user, service.getUsersToShow(user.id), 0));
        response.sendRedirect(Endpoint.USERS);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        renderTemplate(response);
    }
}