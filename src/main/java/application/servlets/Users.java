package application.servlets;

import application.constants.Endpoint;
import application.constants.TemplateName;
import application.entities.SessionData;
import application.models.Choice;
import application.services.ChoiceService;
import application.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class Users extends BaseServlet {
    private final ChoiceService choiceService;
    private final UserService userService;

    public Users() {
        super(TemplateName.USERS);
        this.choiceService = new ChoiceService();
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionData data = getSessionData(request);
        if (data.carouselUserIndex < data.carouselUsers.size()) {
            renderTemplate(response, Map.of("user", data.carouselUsers.get(data.carouselUserIndex)));
        } else {
            response.sendRedirect(Endpoint.LIKES);
            data.carouselUsers = userService.getCarouselUsers(data.userId);
            data.carouselUserIndex = 0;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionData data = getSessionData(request);
        String value = request.getParameter("choice");
        choiceService.addChoice(new Choice(data.getUser(), data.carouselUsers.get(data.carouselUserIndex++), value));
        response.sendRedirect(Endpoint.USERS);
    }
}
