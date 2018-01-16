package ua.nure.antoniuk.web.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("userlastname"));
        System.out.println(request.getParameter("useremail"));
        System.out.println(request.getParameter("userphone"));
        System.out.println(request.getParameter("role"));
        System.out.println(request.getParameter("carmark"));
        System.out.println(request.getParameter("carmodel"));
        System.out.println(request.getParameter("carnumber"));
        System.out.println(request.getParameter("type_bodywork"));
        System.out.println(request.getParameter("capacity"));
        System.out.println(request.getParameter("volume"));
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
