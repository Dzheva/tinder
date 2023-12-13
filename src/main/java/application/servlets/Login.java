package application.servlets;

import application.constants.TemplateName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Login extends BaseServlet {
    public Login() {
        super(TemplateName.LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }
}