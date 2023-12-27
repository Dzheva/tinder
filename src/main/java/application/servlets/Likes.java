package application.servlets;

import application.constants.Endpoint;
import application.constants.TemplateName;
import application.entities.SessionData;
import application.models.Chat;
import application.models.User;
import application.services.ChatService;
import application.services.ChoiceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Likes extends BaseServlet{
    private final ChoiceService choiceService;
    private final ChatService chatService;

    public Likes(ChoiceService choiceService, ChatService chatService) {
        super(TemplateName.LIKES);
        this.choiceService = choiceService;
        this.chatService = chatService;
    }

    public Likes() { this(new ChoiceService(), new ChatService());}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");

        int userId = sessionData.user.id;
        List<User> users = choiceService.getLikedUsers(userId);

        if(users.isEmpty()) {
            response.sendRedirect(Endpoint.USERS);
        } else {
            renderTemplate(response, Map.of("users", users));
        }
    }
}
