package application.servlets;

import application.constants.Endpoint;
import application.constants.TemplateName;
import application.entities.SessionData;
import application.models.Choice;
import application.services.ChoiceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

public class Users extends BaseServlet {
    private final ChoiceService choiceService;

    public Users(ChoiceService choiceService) {
        super(TemplateName.USERS);
        this.choiceService = choiceService;
    }

    public Users() {
        this(new ChoiceService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");

        if (sessionData.nextUserIndex < sessionData.usersToShow.size()) {
            renderTemplate(response, Map.of("user", sessionData.usersToShow.get(sessionData.nextUserIndex)));
        } else {
            response.sendRedirect(Endpoint.LIKES);
            sessionData.nextUserIndex = 0;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        SessionData sessionData = (SessionData) session.getAttribute("sessionData");
        String choiceValue = request.getParameter("choice");
        int initiatorId = sessionData.user.id;
        int targetId = sessionData.usersToShow.get(sessionData.nextUserIndex).id;

        if(choiceService.getChoiceByUsersId(initiatorId, targetId) == null) {
            Choice choice = new Choice(sessionData.user, sessionData.usersToShow.get(sessionData.nextUserIndex), choiceValue);
            choiceService.addChoice(choice);
        } else {
            choiceService.updateChoiceValue(initiatorId, targetId, choiceValue);
        }

        // TODO: 👉 Remove the previous choice if it exists
        //choiceService.addChoice(new Choice(sessionData.user, sessionData.usersToShow.get(sessionData.nextUserIndex), choiceValue));

        sessionData.nextUserIndex++;
        response.sendRedirect(Endpoint.USERS);
    }
}
