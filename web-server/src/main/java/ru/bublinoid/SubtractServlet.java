package ru.bublinoid;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SubtractServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double num1 = Double.parseDouble(request.getParameter("num1"));
        double num2 = Double.parseDouble(request.getParameter("num2"));
        double result = num1 - num2;
        response.getWriter().write("Result: " + result);
    }
}
