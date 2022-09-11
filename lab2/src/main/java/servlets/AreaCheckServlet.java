package servlets;

import beans.Result;
import beans.ResultsBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "AreaCheckServlet", value = "/checkArea")
public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            long startTime = System.nanoTime();
            ResultsBean resultsBean = (ResultsBean) req.getSession().getAttribute("results");
            if (validateAll(req.getParameter("x"), req.getParameter("y"), req.getParameter("r"))) {
                addResults(resultsBean, req.getParameter("x"), req.getParameter("y"), req.getParameter("r"), startTime);
            } else {
                throw new IllegalArgumentException("Неверные значения параметров!");
            }
            req.getSession().setAttribute("results", resultsBean);
        } catch (IllegalArgumentException | NullPointerException e) {
            res.setStatus(400);
            req.setAttribute("message", "-> Ошибка: " + e.getMessage());
        } finally {
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, res);
    }

    private void addResults(ResultsBean resultsBean, String x, String y, String r, long startTime) throws IllegalArgumentException {
        double xVal = Double.parseDouble(x);
        double yVal = Double.parseDouble(y);
        int rVal = Integer.parseInt(r);

        OffsetDateTime currentTimeObject = OffsetDateTime.now();
        String currTime = currentTimeObject.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String execTime = String.valueOf(System.nanoTime() - startTime);

        Result result = new Result(xVal, yVal, rVal, currTime, execTime, checkHit(xVal, yVal, rVal));
        resultsBean.getResults().add(result);
    }

    private boolean validateX(String xString) {
        try {
            double xValue = Double.parseDouble(xString);
            return xValue >= -5 && xValue <= 3;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private boolean validateY(String yString) {
        try {
            double yValue = Double.parseDouble(yString);
            return yValue >= -5 && yValue <= 3;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private boolean validateR(String rString) {
        try {
            List<Integer> rRange = Arrays.asList(1,2,3,4);
            int rValue = Integer.parseInt(rString);
            return rRange.contains(rValue);
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private boolean validateAll(String xString, String yString, String rString) {
        return validateX(xString) && validateY(yString) && validateR(rString);
    }

    private boolean checkHit(double x, double y, int r) {
        return (x <= 0 && y <= 0 && y >= -x/2 - (double)r/2) ||
                (x<=0 && y>=0 && x>=-(double)r/2 && y<=r) ||
                (x>=0 && y<=0 && Math.pow(x,2)+Math.pow(y,2)<=Math.pow(r,2));
    }
}