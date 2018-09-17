package library.servlet.book;


import library.dto.book.ViewBookBasicInfoDto;
import library.dto.book.ViewBookFullInfoDto;
import library.service.BookService;
import library.service.UserService;
import library.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/booksList", name = "BookList")
public class BookListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("user_id"));
        List<ViewBookFullInfoDto> books = BookService.getInstance().findBookUserId(id);
//        req.setAttribute("user_id",id);
        req.setAttribute("books", books);
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("profile-user"))
                .forward(req, resp);
    }
}
