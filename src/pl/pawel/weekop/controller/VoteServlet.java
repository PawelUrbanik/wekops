package pl.pawel.weekop.controller;

import pl.pawel.weekop.model.Discovery;
import pl.pawel.weekop.model.User;
import pl.pawel.weekop.model.Vote;
import pl.pawel.weekop.model.VoteType;
import pl.pawel.weekop.service.DiscoveryService;
import pl.pawel.weekop.service.VoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
example URL : /vote?discovery_di=3&vote=Vote.UP
 */

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("user");
        if (loggedUser != null)
        {
            VoteType voteType = VoteType.valueOf(request.getParameter("vote"));
            long userId = loggedUser.getId();
            long discovery_id = Long.parseLong(request.getParameter("discovery_id"));
            System.out.println(userId + " " + discovery_id + " " + voteType);
            updateVote(userId, discovery_id, voteType);
        }
        response.sendRedirect(request.getContextPath() +"/");
    }

    private void updateVote(long userId, long discovery_id, VoteType voteType)
    {
        VoteService voteService = new VoteService();
        Vote existingVote =voteService.getVoteByDiscoveryUserId(discovery_id,userId);
        //System.out.println("Existing vote "+ existingVote);
        Vote updatedVote = voteService.addOrUpdateVote(discovery_id,userId ,voteType);
        //System.out.println("updated vote "+ updatedVote);
        if (existingVote != updatedVote && !updatedVote.equals(existingVote))
        {

            updateDiscovery(discovery_id, existingVote, updatedVote);
            System.out.println("Update discovery");
        }

    }

    private void updateDiscovery(long discovery_id, Vote oldVote, Vote newVote)
    {
        DiscoveryService service  = new DiscoveryService();
        Discovery discoveryById = service.getDiscoveryById(discovery_id);
        //System.out.println("Discovery By Id: "+ discovery_id );
        Discovery updatedDiscovery = null;
        if (oldVote == null && newVote != null)
        {
            updatedDiscovery = addDiscoveryVote(discoveryById, newVote.getVoteType());
        }
        else if (oldVote != null && newVote != null)
        {
            updatedDiscovery = removeDiscovery(discoveryById, oldVote.getVoteType());
            updatedDiscovery = addDiscoveryVote(discoveryById, newVote.getVoteType());
        }
        service.updateDiscovery(updatedDiscovery);
    }

    private Discovery addDiscoveryVote(Discovery discovery, VoteType voteType)
    {
    Discovery discoveryCopy = new Discovery(discovery);
    if (voteType == VoteType.VOTE_UP)
        {
            discoveryCopy.setUpVote(discoveryCopy.getUpVote() + 1);
        }
    else if (voteType == VoteType.VOTE_DOWN)
        {
            discoveryCopy.setDownVote(discoveryCopy.getDownVote() + 1);
        }
    return discoveryCopy;
    }

    private Discovery removeDiscovery(Discovery discovery, VoteType voteType)
    {
        Discovery discoveryCopy = new Discovery(discovery);
        if (voteType == VoteType.VOTE_DOWN)
        {
            discoveryCopy.setDownVote(discovery.getDownVote() -1);
        }
        else if (voteType == VoteType.VOTE_UP)
        {
            discoveryCopy.setUpVote(discovery.getUpVote() -1);
        }
        return discoveryCopy;
    }
}
