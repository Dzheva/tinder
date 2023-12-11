package application.servlets;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public abstract class BaseServlet extends HttpServlet {
    private final PebbleTemplate template;

    public BaseServlet(String template) {
        super();
        PebbleEngine engine = new PebbleEngine.Builder().autoEscaping(true).build();
        this.template = engine.getTemplate("templates/" + template + ".peb");
    }

    public void renderTemplate(PrintWriter writer, Map<String, Object> context) throws IOException {
        template.evaluate(writer, context);
    }
}
