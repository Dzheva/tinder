package application.servlets;

import application.constants.Endpoint;
import application.constants.TemplateName;
import application.entities.SessionData;
import application.models.Chat;
import application.models.Message;
import application.services.ChatService;
import application.services.ChoiceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

public class ChatServlet extends BaseServlet{

    public ChatServlet() {
        super(TemplateName.CHAT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");
        Chat chat = sessionData.chat;

        if(chat != null){
            renderTemplate(response, Map.of("chat", chat, "messages", chat.messages));
        } else {
            response.sendRedirect(Endpoint.LIKES);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");
        Chat chat = sessionData.chat;
        String text = request.getParameter("text");

        if(!text.isEmpty()){
            Message message = new Message(chat, sessionData.user, System.currentTimeMillis(), text);
            chat.messages.add(message);
        }

        response.sendRedirect(Endpoint.CHAT);
    }


}
