package servlets;

import common.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");

        UserHandler userHandler = new UserHandler();
        boolean isRegistered = userHandler.registerUser(username, password, name, email, phoneNumber, gender, birthdate);

        if (isRegistered) {
            response.sendRedirect("index.jsp?message=register-success");
        } else {
            response.sendRedirect("register.jsp?error=register-failed");
        }
    }
}
