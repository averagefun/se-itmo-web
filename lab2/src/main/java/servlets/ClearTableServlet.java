package servlets;

import data.ResultList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ClearTableServlet", value = "/clear")
public class ClearTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        ResultList resultList;
        if (session.getAttribute("results") == null) {
            resultList = new ResultList();
        } else {
            resultList = (ResultList) session.getAttribute("results");
        }

        resultList.clear();
        session.setAttribute("results", resultList);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, res);
    }
}
