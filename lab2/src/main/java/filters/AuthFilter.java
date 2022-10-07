package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String user = req.getParameter("username");
        String password = req.getParameter("password");
        if ((user != null) && (password != null)) {
            ServletContext context = getServletContext();
            @SuppressWarnings("unchecked") Map<String, String> users = (Map<String, String>)context.getAttribute("users");
            if (users.containsKey(user) && users.get(user).equals(password)) {
                chain.doFilter(req, res);
            } else {
                req.setAttribute("message", "Неправильный логин или пароль.");
                res.setStatus(401);
                req.getRequestDispatcher("index.jsp").forward(req, res);

            }
        } else {
            req.setAttribute("message", "Не введён логин или пароль.");
            res.setStatus(401);
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
    }
}
