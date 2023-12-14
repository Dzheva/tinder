package application.servlets;

import application.constants.ContentType;
import application.constants.TemplateName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class Index extends BaseServlet {
    public Index() {
        super(TemplateName.INDEX);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(ContentType.HTML);
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> context = Map.of("message", "Hello World!");

        try {
            renderTemplate(response.getWriter(), context);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}