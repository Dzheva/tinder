package application.servlets;

import application.constants.ChoiceValue;
import application.constants.Endpoint;
import application.constants.TemplateName;
import application.entities.SessionData;
import application.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Likes extends BaseServlet {
    public Likes() {
        super(TemplateName.LIKES);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionData data = getSessionData(request);
        List<User> users = data.getUser().choices.stream()
                .filter(choice -> choice.value.equals(ChoiceValue.LIKE)).map(choice -> choice.target).toList();
        if (users.isEmpty()) {
            response.sendRedirect(Endpoint.USERS);
        } else {
            renderTemplate(response, Map.of("users", users));
        }
    }
}
