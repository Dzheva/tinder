package application.servlets;

import application.constants.Endpoint;
import application.constants.TemplateName;
import application.entities.SessionData;
import application.models.Chat;
import application.models.Message;
import application.models.User;
import application.repositories.Repository;
import application.services.ChatService;
import application.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Messages extends BaseServlet {
    public Messages() {
        super(TemplateName.MESSAGES);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");
        String pathInfo = request.getPathInfo();
        System.out.println(pathInfo);
        UserService service = new UserService();
        User targetUser = service.getUserByUsername(pathInfo.substring(1));
        Chat chat = new ChatService().getChatBetweenUsers(sessionData.user.id, targetUser.id);
        if (chat == null) {
            chat = new Chat(List.of(sessionData.user, targetUser));
            new Repository().addEntity(chat);
        }
        renderTemplate(response, Map.of("target", targetUser, "chat", chat));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
