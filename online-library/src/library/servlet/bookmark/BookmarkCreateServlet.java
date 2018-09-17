package library.servlet.bookmark;


import library.dto.book.CreateNewBookDto;
import library.dto.book.ViewBookBasicInfoDto;
import library.dto.bookmark.CreateNewBookmarkDto;
import library.service.BookService;
import library.service.BookmarkService;
import library.service.GenreService;
import library.util.JspPathUtil;
import library.util.WriterFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig
@WebServlet(value = "/createBookmark", name = "CreateBookmark")
public class BookmarkCreateServlet extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("genres", GenreService.getInstance().findAll());
//        req.setAttribute("user_id", req.getParameter("user_id"));
//        getServletContext()
//                .getRequestDispatcher(JspPathUtil.get("create-book"))
//                .forward(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateNewBookmarkDto createNewBookmarkDto = CreateNewBookmarkDto.builder()
                .bookId(Long.valueOf(req.getParameter("book_id")))
                .idUser(Long.valueOf(req.getParameter("user_id")))
                .build();
        BookmarkService.getInstance().save(createNewBookmarkDto);
        resp.sendRedirect(req.getHeader("Referer"));
    }


}
