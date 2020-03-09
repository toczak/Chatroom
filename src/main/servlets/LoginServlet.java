package main.servlets;

import main.DAO.UserDao;
import main.model.UserEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String login = request.getParameter("inputLogin");
        String password = request.getParameter("inputPassword");

        if (login.isEmpty() || password.isEmpty()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index");
            request.setAttribute("infoLogin", "<script>alert('Please fill all fields!');</script>");
            requestDispatcher.include(request, response);
        } else {
            UserEntity user = userDao.validateLoginAndReturnUser(login, login, password);
            if (user!=null) {
                request.getSession().setAttribute("user", user);
                RequestDispatcher rd = request.getRequestDispatcher("index");
                rd.forward(request, response);

            } else {
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                request.setAttribute("infoLogin", "<script>alert('Incorrect login/email or password!!');</script>");
                rd.include(request, response);
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        RequestDispatcher requestDispatcher;
        if (request.getSession().getAttribute("user") == null)
            requestDispatcher = request.getRequestDispatcher("login.jsp");
        else
            requestDispatcher = request.getRequestDispatcher("/index");

        requestDispatcher.forward(request, response);

    }
}
