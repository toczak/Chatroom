package main.servlets;

import main.DAO.PostDao;
import main.DAO.UserDao;
import main.model.UserEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.regex.Pattern;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("inputNick");
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
        String password2 = request.getParameter("inputPassword2");

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
            out.println("<font color=red><h2>Fill all fields!</h2></font>");
            requestDispatcher.include(request, response);
        } else {
            boolean correctValue = true;

            request.setAttribute("name", name);
            if (!Pattern.matches("[\\w]{2,20}", name)) {
                correctValue = false;
                request.setAttribute("resultName", "<font color=red><i>Nick is incorrect!</i></font><br/>");
            }

            request.setAttribute("email", email);
            if (!Pattern.matches("[\\w.%+-]+@[\\w.-]+\\.[a-zA-z]{2,4}", email)) {
                correctValue = false;
                request.setAttribute("resultEmail", "<font color=red><i>E-mail is incorrect!</i></font><br/>");
            }

            if (!Pattern.matches(".{8,}", password)) {
                correctValue = false;
                request.setAttribute("resultPassword", "<font color=red><i>This password doesn't have at least 8 characters!</i></font><br/>");
            }

            if (!password.equals(password2)) {
                correctValue = false;
                request.setAttribute("resultPassword2", "<font color=red><i>Passwords are not the same!</i></font><br/>");
            }

            if (!correctValue) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
                out.println("<script>alert('Problem with registration! Check all fields.');</script>");
                requestDispatcher.include(request, response);
            } else {
                //if user exists - return false
                if(!userDao.checkUserBeforeRegister(name,email)){
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
                    out.println("<script>alert('User with this email/login is exists!');</script>");
                    requestDispatcher.include(request, response);
                } else {
                    UserEntity user = new UserEntity();
                    user.setUsername(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    RequestDispatcher rd = request.getRequestDispatcher("index");
                    request.setAttribute("infoRegister", "<script>alert('Registration correct! You can login now.');</script>");
                    userDao.saveUser(user);
                    rd.forward(request, response);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        RequestDispatcher requestDispatcher;
        if(request.getSession().getAttribute("user")==null)
            requestDispatcher = request.getRequestDispatcher("register.jsp");
        else
            requestDispatcher = request.getRequestDispatcher("/index");
        requestDispatcher.forward(request, response);
    }
}
