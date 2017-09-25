package pl.pawel.weekop.controller;

import pl.pawel.weekop.model.Discovery;
import pl.pawel.weekop.model.User;
import pl.pawel.weekop.service.DiscoveryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class DiscoveryAddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getUserPrincipal() != null)
        {
            request.getRequestDispatcher("WEB-INF/new.jsp").forward(request,response);
        }
        else
        {
            response.sendError(403);
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("inputName");
        String desc  = request.getParameter("inputDescription");
        String url = request.getParameter("inputUrl");
        User userAuth = (User) request.getSession().getAttribute("user");
        if (request.getUserPrincipal() != null)
        {
            DiscoveryService discoveryService = new DiscoveryService();
            discoveryService.addDiscovery(name,desc,url,userAuth);
            response.sendRedirect(request.getContextPath()+ "/");
        }
        else
        {
            response.sendError(403);
        }
    }
}
