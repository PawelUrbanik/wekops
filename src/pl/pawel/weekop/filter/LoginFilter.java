package pl.pawel.weekop.filter;

import pl.pawel.weekop.model.User;
import pl.pawel.weekop.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        if(httpReq.getUserPrincipal() != null && httpReq.getSession().getAttribute("user") == null) {
            saveUserInSession(httpReq);
        }
        chain.doFilter(request, response);
    }
    private void saveUserInSession(HttpServletRequest request) {
        UserService userService = new UserService();
        String username = request.getUserPrincipal().getName();
        User userByName = userService.getUserByUsername(username);
        System.out.println(userByName + " " + username);
        request.getSession(true).setAttribute("user", userByName);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
