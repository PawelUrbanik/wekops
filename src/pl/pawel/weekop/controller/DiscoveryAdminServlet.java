package pl.pawel.weekop.controller;

import pl.pawel.weekop.model.Discovery;
import pl.pawel.weekop.service.DiscoveryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/discoveryAdmin")
public class DiscoveryAdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveDiscoveryInRequst(request);
    request.getRequestDispatcher("WEB-INF/discoveryAdmin.jsp").forward(request,response);
    }

    private void saveDiscoveryInRequst(HttpServletRequest request) {
        DiscoveryService discoveryService = new DiscoveryService();

        List<Discovery> discoveries = discoveryService.getAllDiscoveries((d1,d2)->d1.compareTo(d2));
        request.setAttribute("discoveriesId", discoveries);
        System.out.println(request.getAttribute("discoveriesId"));
    }
}
