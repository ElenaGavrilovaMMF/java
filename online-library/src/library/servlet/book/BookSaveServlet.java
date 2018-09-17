package library.servlet.book;

import library.service.GenreService;
import library.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/saveBook", name = "SaveBook")
public class BookSaveServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("genres", GenreService.getInstance().findAll());
  //      req.setAttribute("user_id",req.getParameter("user_id"));
        resp.sendRedirect("/bookFullInfo");
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("create-book"))
                .forward(req, resp);
    }
}
