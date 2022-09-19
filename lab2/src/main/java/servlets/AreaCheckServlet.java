package servlets;

import data.Result;
import data.ResultList;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long startTime = System.nanoTime(); // start time of request

            HttpSession session = request.getSession();

            ResultList resultList;
            if (session.getAttribute("results") == null) {
                resultList = new ResultList();
            } else {
                resultList = (ResultList) session.getAttribute("results");
            }

            if (validateAll(request.getParameter("x"), request.getParameter("y"), request.getParameter("r"))) {
                Result newResult = getNewResult(request.getParameter("x"), request.getParameter("y"), request.getParameter("r"), startTime);
                resultList.addNewResult(newResult);
            } else {
                throw new IllegalArgumentException("Неверные значения параметров!");
            }

            session.setAttribute("results", resultList);
        } catch (IllegalArgumentException | NullPointerException e) {
            response.setStatus(400);
            request.setAttribute("message", "Ошибка: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, res);
    }

    private Result getNewResult(String x, String y, String r, long startTime) throws IllegalArgumentException {
        double xVal = Double.parseDouble(x);
        double yVal = Double.parseDouble(y);
        int rVal = Integer.parseInt(r);

        OffsetDateTime currentTimeObject = OffsetDateTime.now();
        String currTime = currentTimeObject.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String execTime = String.valueOf(System.nanoTime() - startTime);

        return new Result(xVal, yVal, rVal, currTime, execTime, checkHit(xVal, yVal, rVal));
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