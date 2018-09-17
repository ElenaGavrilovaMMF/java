package library.servlet.bookmark;


import library.dto.book.ViewBookFullInfoDto;
import library.dto.bookmark.ViewBookmarkFullInfoDto;
import library.service.BookmarkService;

import library.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(value = "/bookmarkFullInfo", name = "BookmarkFullInf")
public class BookmarkFullInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ViewBookmarkFullInfoDto> viewBookmarkFullInfoDto = BookmarkService.getInstance().findBookUserId(Long.valueOf(req.getParameter("user_id")));
        List<ViewBookFullInfoDto> book = new ArrayList();
        for (int i=0;i<viewBookmarkFullInfoDto.size();i++){
            ViewBookFullInfoDto viewBookFullInfoDto = viewBookmarkFullInfoDto.get(i).getBook();
            book.add(viewBookFullInfoDto);
        }

        req.setAttribute("books", book);
        req.setAttribute("user_id",req.getParameter("user_id"));

        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("view_bookmark"))
                .forward(req, resp);
    }
}

