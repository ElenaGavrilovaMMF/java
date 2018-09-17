package library.servlet.book;


import library.dao.BookDao;
import library.dto.ban.ViewBanDto;
import library.dto.book.ViewBookBasicInfoDto;
import library.dto.book.ViewBookFullInfoDto;
import library.dto.book.ViewBookTextDto;
import library.dto.chapter.ViewChapterBasicInfoDto;
import library.dto.chapter.ViewChapterFullInfoDto;
import library.dto.comment.ViewCommentBasicInfoDto;
import library.dto.comment.ViewCommentFullInfoDto;
import library.entity.Book;
import library.service.BanService;
import library.service.BookService;
import library.service.ChapterService;
import library.service.CommentService;
import library.util.JspPathUtil;
import library.util.WriterFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(value = "/bookFullInfo", name = "BookFullInf")
public class BookFullInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("book_id");
        if (id != null) {
            List<ViewChapterBasicInfoDto> chapterBasicInfoDto = ChapterService.getInstance().findAll(Long.valueOf(id));
            ViewBookFullInfoDto bookFullInfoDto = BookService.getInstance().findById(Long.valueOf(id));
            Optional<Book> byId = BookDao.getInstance().findById(Long.valueOf(id));
            List<ViewCommentFullInfoDto> commentFullInfoDto = CommentService.getInstance().findAllBookId(Long.valueOf(id));
            req.setAttribute("comments",commentFullInfoDto);
            req.setAttribute("book_user_id",byId.get().getIdUser());
            req.setAttribute("book", bookFullInfoDto);
            req.setAttribute("chapters",chapterBasicInfoDto);
            ViewBanDto ban = BanService.getInstance().findById(Long.valueOf(req.getParameter("book_id")));
            if(ban!=null){
            req.setAttribute("ban",ban);
            }else {
                req.setAttribute("ban",null);

            }
       //     req.setAttribute("user_id",req.getParameter("user_id"));
            req.setAttribute("book_id",id);

            getServletContext()
                    .getRequestDispatcher(JspPathUtil.get("view-full-book"))
                    .forward(req, resp);
        }
    }
}
