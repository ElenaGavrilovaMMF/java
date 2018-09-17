package library.servlet.bookmark;


import library.dto.bookmark.CreateNewBookmarkDto;
import library.service.BookmarkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet(value = "/deleteBookmark", name = "DeleteBookmark")
public class DeleteBookmarkServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookmarkService.getInstance().deleteMark(Long.valueOf(req.getParameter("book_id")));
        resp.sendRedirect(req.getHeader("Referer"));
    }


}
