package application.servlets;

import application.constants.Endpoint;
import application.constants.TemplateName;
import application.entities.SessionData;
import application.models.Choice;
import application.models.User;
import application.services.ChoiceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Likes extends BaseServlet{
    private final ChoiceService choiceService;

    public Likes(ChoiceService choiceService) {
        super(TemplateName.LIKES);
        this.choiceService = choiceService;
    }

    public Likes() { this(new ChoiceService());}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");

        int userId = sessionData.user.id;
        List<User> likedUsers = choiceService.getLikedUsers(userId);

        if(likedUsers.isEmpty()) {
            response.sendRedirect(Endpoint.USERS);
        } else {
            renderTemplate(response, Map.of("likedUsers", likedUsers));
        }

    }
}
