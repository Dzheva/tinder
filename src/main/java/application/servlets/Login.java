package application.servlets;

import application.constants.ContentType;
import application.constants.TemplateName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Login extends BaseServlet {
    public Login() {
        super(TemplateName.LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(ContentType.HTML);
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            renderTemplate(response.getWriter(), Map.of());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}