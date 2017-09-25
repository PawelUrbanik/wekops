package pl.pawel.weekop.controller;

import pl.pawel.weekop.model.Discovery;
import pl.pawel.weekop.service.DiscoveryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    saveDiscoveriesInReques(request);
    request.getRequestDispatcher("WEB-INF/index.jsp").forward(request,response);
    }

    private void saveDiscoveriesInReques(HttpServletRequest request) {
        DiscoveryService service  = new DiscoveryService();
        List<Discovery> allDiscoveries = service.getAllDiscoveries(new Comparator<Discovery>() {
            @Override
            public int compare(Discovery d1, Discovery d2) {
                int d1Vote = d1.getUpVote() - d1.getDownVote();
                int d2Vote = d2.getUpVote() - d2.getDownVote();
                if (d1Vote < d2Vote)
                {
                    return 1;
                }
                else if (d1Vote > d2Vote)
                {
                    return -1;
                }
                return 0;
            }
        });
        request.setAttribute("discoveries", allDiscoveries);
    }
}
