package pl.pawel.weekop.controller;

import pl.pawel.weekop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (request.getUserPrincipal() != null)
    {
        System.out.println(request.getUserPrincipal().getName());
        request.getSession().setAttribute("username",request.getUserPrincipal().getName());
        if (request.getUserPrincipal().getName().equals("admin"))
        {
            response.sendRedirect(request.getContextPath()+ "/adminPage");
        }
        else
        response.sendRedirect(request.getContextPath() + "/");
    }
    else
    {
        response.sendError(403);
    }
    }
}
