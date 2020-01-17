package main.servlets;

import main.DAO.PostDao;
import main.model.PostEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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
                case "/":
                    listPost(request,response);
                    break;
                default:
                    listPost(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listPost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<PostEntity> listPost = postDao.getAllPost();
        request.setAttribute("listPost", listPost);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
