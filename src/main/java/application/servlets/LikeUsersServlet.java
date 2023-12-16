package application.servlets;

import application.constants.TemplateName;
import application.models.Choice;
import application.models.User;
import application.repositories.ChoiceRepository;
import application.repositories.UserRepository;
import application.services.ChoiceService;
import application.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LikeUsersServlet extends BaseServlet {

    private final UserService userService;
    private final ChoiceService choiceService;

    public LikeUsersServlet(UserService userService, ChoiceService choiceService) {
        super(TemplateName.USERS);
        this.userService = userService;
        this.choiceService = choiceService;

    }

    public LikeUsersServlet() {
        super(TemplateName.USERS);
        this.userService = new UserService(new UserRepository());
        this.choiceService = new ChoiceService(new ChoiceRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int initiatorId = getInitiatorId(req);
            List<User> usersToShow = userService.getUsersToShow(initiatorId);

            String targetIndexParam = req.getParameter("targetIndex");
            int targetIndex = (targetIndexParam != null) ? Integer.parseInt(targetIndexParam) : 0;

            if (targetIndex < usersToShow.size()) {
                User targetUser = usersToShow.get(targetIndex);
                targetIndex++;
                //req.setAttribute("targetUser", targetUser);
                //req.getRequestDispatcher(TemplateName.USERS).forward(req, resp);
                renderTemplate(resp.getWriter(), Map.of("fullName", targetUser.fullName));
            } else {
                // Если список закончился, перенаправляем на страницу "/likes"
                resp.sendRedirect("/likes");
            }

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid initiatorId parameter");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Извлекаем параметры выбора из запроса
            String value = req.getParameter("choice");
            int initiatorId = Integer.parseInt(req.getParameter("initiatorId"));
            int targetIndex = Integer.parseInt(req.getParameter("targetIndex"));
            System.out.println(value);
            System.out.println(initiatorId);
            System.out.println(targetIndex);

            //Получаем юзеров для сохранения выбора
            User initiatorUser = userService.getUserById(initiatorId);
            List<User> usersToShow = userService.getUsersToShow(initiatorId);
            User targetUser = usersToShow.get(targetIndex);

            // Сохраняем выбор в базе данных
            choiceService.addChoice(new Choice(initiatorUser, targetUser, value));

            // Перенаправляем пользователя на следующего юзера
            resp.sendRedirect("/users?initiatorId=" + initiatorId + "&targetIndex=" + (targetIndex + 1));


        } catch (NumberFormatException e) {
            // Обработка ошибок при парсинге параметров
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter");
        } catch (Exception e) {
            // Обработка других ошибок
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    private int getInitiatorId(HttpServletRequest req) {
        String initiatorIdParam = req.getParameter("initiatorId");
        if (initiatorIdParam != null) {
            return Integer.parseInt(initiatorIdParam);
        } else {
            throw new NumberFormatException("initiatorId parameter is missing or empty");
        }
    }
}
