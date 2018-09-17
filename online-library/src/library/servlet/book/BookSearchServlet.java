package library.servlet.book;


import library.dao.BookDao;
import library.dto.book.ViewBookBasicInfoDto;
import library.dto.book.ViewBookFullInfoDto;
import library.service.BookService;
import library.service.GenreService;
import library.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/bookScearch", name = "BookScearch")
public class BookSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("genres", GenreService.getInstance().findAll());
  //      req.setAttribute("user_id",req.getParameter("user_id"));
        List<ViewBookFullInfoDto> books = BookService.getInstance().findAllUser();
        req.setAttribute("books", books);

        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("search-book"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("genres", GenreService.getInstance().findAll());
        int genre = Integer.valueOf(req.getParameter("genre"));
        String name = req.getParameter("name");
        List<ViewBookFullInfoDto> bookBasicInfoDto = new ArrayList<>();
        if (name == "" && genre != 0) {
            bookBasicInfoDto = BookService.getInstance().findAll(genre);
            req.setAttribute("genre", genre);
            req.setAttribute("books", bookBasicInfoDto);

        } else {
            bookBasicInfoDto = BookService.getInstance().findAllUser(name);
            if (name != "" && genre != 0 && bookBasicInfoDto.size()!=0) {
                bookBasicInfoDto = BookService.getInstance().findAll(genre, name);
                req.setAttribute("name", name);
                req.setAttribute("genre", genre);
                req.setAttribute("books", bookBasicInfoDto);

            }
            if (name != "" && genre == 0) {
                req.setAttribute("name", name);
                bookBasicInfoDto = BookService.getInstance().findAllUser(name);
                req.setAttribute("books", bookBasicInfoDto);

            }
            if (name != "" && genre == 0 && bookBasicInfoDto.size()==0) {
                req.setAttribute("name", name);
                bookBasicInfoDto = BookService.getInstance().findAll(name);
                req.setAttribute("books", bookBasicInfoDto);

            }
            if (name != "" && genre != 0 && bookBasicInfoDto.size()==0) {
                req.setAttribute("name", name);
                req.setAttribute("genre", genre);
                bookBasicInfoDto = BookService.getInstance().findAllBookGenre(name,genre);
                req.setAttribute("books", bookBasicInfoDto);
            }
        }
        req.setAttribute("user_id",req.getParameter("user_id"));
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("search-book"))
                .forward(req, resp);

    }

}
