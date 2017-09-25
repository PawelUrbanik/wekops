package pl.pawel.weekop.controller;

import pl.pawel.weekop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("WEB-INF/register.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String username = request.getParameter("inputUsername");
    String password = request.getParameter("inputPassword");
    String email  = request.getParameter("inputEmail");
        UserService service = new UserService();
        service.addUser(username,email,password);
        response.sendRedirect(request.getContextPath()+"/");
    }
}
