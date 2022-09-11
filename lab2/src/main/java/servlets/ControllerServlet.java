package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", value = "/process")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if (req.getParameter("clear") != null && req.getParameter("clear").equals("true")) {
            req.getRequestDispatcher("/clear").forward(req, res);
        } else if (req.getParameter("x") != null && req.getParameter("y") != null && req.getParameter("r") != null) {
            // validate parameters
            req.getRequestDispatcher("/checkArea").forward(req, res);
        } else {
            // error message
            req.setAttribute("message", "-> Ошибка: переданы не все параметры!");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
