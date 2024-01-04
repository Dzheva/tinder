package application.servlets;

import application.constants.ContentType;
import application.entities.SessionData;
import application.exceptions.NoTemplateException;
import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

public abstract class BaseServlet extends HttpServlet {
    private final PebbleTemplate template;

    public BaseServlet(String template) {
        super();
        PebbleEngine engine = new PebbleEngine.Builder().autoEscaping(true).build();
        this.template = engine.getTemplate("templates/" + template + ".peb");
    }

    public BaseServlet() {
        super();
        this.template = null;
    }

    public void renderTemplate(HttpServletResponse response, Map<String, Object> context) throws IOException {
        if (template == null) throw new NoTemplateException();
        response.setContentType(ContentType.HTML);
        response.setStatus(HttpServletResponse.SC_OK);
        template.evaluate(response.getWriter(), context);
    }

    public void renderTemplate(HttpServletResponse response) throws IOException {
        renderTemplate(response, Map.of());
    }

    public SessionData getSessionData(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (SessionData) session.getAttribute("data");
    }
}
