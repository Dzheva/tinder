package application.servlets;

import application.Utils.CommonUtils;
import application.constants.Endpoint;
import application.constants.TemplateName;
import application.entities.SessionData;
import application.models.Chat;
import application.models.Message;
import application.models.User;
import application.services.ChatService;
import application.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

public final class Messages extends BaseServlet {
    private final Pattern pathPattern = Pattern.compile("^/(?<username>[^/]+)$");
    private final ChatService chatService;
    private final UserService userService;

    public Messages() {
        super(TemplateName.MESSAGES);
        chatService = new ChatService();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionData data = getSessionData(request);
        String username = CommonUtils.findRegexGroup(pathPattern, request.getPathInfo(), "username");
        User targetUser = userService.getUser(username);

        if (targetUser == null || targetUser.id == data.userId) {
            response.sendRedirect(Endpoint.LIKES);
            return;
        }
        Chat chat = chatService.getChat(data.getUser(), targetUser);
        data.chatTargetUserId = targetUser.id;
        renderTemplate(response, Map.of("target", targetUser, "chat", chat));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String text = request.getParameter("text");
        SessionData data = getSessionData(request);
        User targetUser = data.getChatTargetUser();

        if (targetUser == null) {
            response.sendRedirect(Endpoint.LIKES);
            return;
        }
        if (!text.trim().isEmpty()) {
            Chat chat = chatService.getChat(data.getUser(), targetUser);
            chatService.createMessage(new Message(chat, data.getUser(), System.currentTimeMillis(), text));
        }
        response.sendRedirect("/messages/" + targetUser.username);
    }
}
