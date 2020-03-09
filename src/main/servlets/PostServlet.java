package main.servlets;

import javafx.geometry.Pos;
import main.DAO.PostDao;
import main.model.PostEntity;
import main.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;

@WebServlet(name = "PostServlet")
public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PostDao postDao;

    public void init() {
        postDao = new PostDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/search":
                    listPostByUser(request, response);
                    break;
                case "/add":
                    addMessage(request, response);
                    break;
                case "/":
                    listPost(request, response);
                    break;
                default:
                    listPost(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listPostByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("username").equals("") && request.getParameter("username") != null && !request.getParameter("username").isEmpty())
            request.setAttribute("listPost", postDao.getAllPostByUser(request.getParameter("username")));
        else
            request.setAttribute("listPost", null);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void addMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("buttonSendMessage") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index");
            dispatcher.forward(request, response);
            return;
        }
        if (!request.getParameter("textareaMessage").isEmpty() && !request.getParameter("textareaMessage").equals("")) {
            PostEntity post = new PostEntity();
            post.setText(request.getParameter("textareaMessage"));
            post.setDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
            post.setUserByIdUser((UserEntity) request.getSession().getAttribute("user"));
            postDao.savePost(post);
            System.out.println(" dodalem");

            RequestDispatcher dispatcher = request.getRequestDispatcher("index");
            dispatcher.forward(request, response);
        } else {
            System.out.println("nie dodalem");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index");
            dispatcher.forward(request, response);
        }
    }

    private void listPost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<PostEntity> listPost = postDao.getAllPost(applyLatestDirection(request));
        request.setAttribute("listPost", listPost);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private boolean applyLatestDirection(HttpServletRequest request) {
        request.setAttribute("sortByLatest", true);
        if (request.getParameter("selectSort") == null) {
            return true;
        } else {
            if (request.getParameter("selectSort").equals("old")) {
                request.setAttribute("sortByLatest", false);
                return false;
            } else {
                return true;
            }
        }
    }


}
