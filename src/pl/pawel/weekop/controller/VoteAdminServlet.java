package pl.pawel.weekop.controller;

import pl.pawel.weekop.model.Vote;
import pl.pawel.weekop.service.UserService;
import pl.pawel.weekop.service.VoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/voteAdmin")
public class VoteAdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveVoteInRequest(request);
        request.getRequestDispatcher("WEB-INF/voteAdmin.jsp").forward(request,response);
    }

    private void saveVoteInRequest(HttpServletRequest request)
    {
        VoteService voteService = new VoteService();
        List<Vote> votes = voteService.getAllVotes((v1,v2)->v1.compareTo(v2));
        request.setAttribute("votes", votes);
        //System.out.println("voteAdminServlet: "+request.getAttribute("votes"));

    }
}
