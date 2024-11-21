package Servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import membership.MemberDAO;
import membership.MemberDTO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber"); // 입력받는 필드는 그대로 사용
        String birthdateStr = request.getParameter("birthdate");
        String gender = request.getParameter("gender");

        
        System.out.println("===================회원가입 서블릿 적용확인=====================");
        System.out.println("username=  "+ username);
        System.out.println("password=  "+ password);
        System.out.println("name=  "+ name);
        System.out.println("email=  "+ email);
        System.out.println("phoneNumber=  "+ phoneNumber);
        System.out.println("birthdateStr=  "+ birthdateStr);
        System.out.println("gender=  "+ gender);
        
        Date birthdate = null;
        try {
            birthdate = Date.valueOf(birthdateStr);
        } catch (IllegalArgumentException e) {
            response.getWriter().println("<script>alert('잘못된 생년월일 형식입니다.'); location.href='register.jsp';</script>");
            return;
        }

        MemberDTO member = new MemberDTO(username, password, name, email, phoneNumber, gender, birthdate);

        MemberDAO dao = new MemberDAO(getServletContext());
        boolean isRegistered = dao.registerMember(member);

        if (isRegistered) {
            response.sendRedirect("index.jsp");
        } else {
            response.getWriter().println("<script>alert('회원가입 실패!'); location.href='register.jsp';</script>");
        }
    }
}
