package pl.pawel.weekop.controller;

import pl.pawel.weekop.model.User;
import pl.pawel.weekop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/userAdmin")
public class UserAdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveUserInRequst(request);
    request.getRequestDispatcher("WEB-INF/userAdmin.jsp").forward(request,response);
    }

    private void saveUserInRequst(HttpServletRequest request) {
        UserService userService = new UserService();

        List<User> allUsers = userService.getAllUsers((u1, u2) -> u1.compareTo(u2));
       /* List<User> allUsers = userService.getAllUsers(new Comparator<User>()
        {

            @Override
            public int compare(User o1, User o2) {
                return o1.compareTo(o2);
            }
        });*/
        request.setAttribute("users", allUsers);
        System.out.println(request.getAttribute("users"));
    }
}
