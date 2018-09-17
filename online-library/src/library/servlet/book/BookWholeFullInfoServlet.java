package library.servlet.book;


import library.dao.BookDao;
import library.dto.book.ViewBookFullInfoDto;
import library.dto.chapter.ViewChapterFullInfoDto;
import library.service.BookService;
import library.service.ChapterService;
import library.util.JspPathUtil;
import library.util.PageUtil;
import library.util.WriterFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/bookWholeFullInfo", name = "BookWholeFullInfo")
public class BookWholeFullInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("book_id");
        if (id != null) {
            String fileName = BookService.getInstance().findByIdText(Long.valueOf(req.getParameter("book_id"))).getUrl();
            ViewBookFullInfoDto book = BookService.getInstance().findById(Long.valueOf(id));
            String contents = WriterFile.readUsingBufferedReader(BookService.getInstance().findByIdText(Long.valueOf(id)).getUrl());
            req.setAttribute("book", book);
            req.setAttribute("page",BookService.getInstance().findAllPageWhole(fileName,1));
            req.setAttribute("allpages", PageUtil.countPageString(fileName));
    //        req.setAttribute("contents",contents);
            req.setAttribute("user_id",req.getParameter("user_id"));
            req.setAttribute("book_id",req.getParameter("book_id"));
            req.setAttribute("numberPage",1);
            getServletContext()
                    .getRequestDispatcher(JspPathUtil.get("view-full-whole-book"))
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("book_id");
        ViewBookFullInfoDto book = BookService.getInstance().findById(Long.valueOf(id));
        req.setAttribute("book", book);
        String fileName = BookService.getInstance().findByIdText(Long.valueOf(req.getParameter("book_id"))).getUrl();
        req.setAttribute("page",BookService.getInstance().findAllPageWhole(fileName,Integer.parseInt(req.getParameter("numberPage"))));
        req.setAttribute("allpages", PageUtil.countPageString(fileName));
    //    req.setAttribute("user_id",req.getParameter("user_id"));
        req.setAttribute("book_id",req.getParameter("book_id"));
        req.setAttribute("numberPage",req.getParameter("numberPage"));
//        resp.sendRedirect("/bookWholeFullInfo?user_id="+req.getParameter("user_id")+"&book_id="+req.getParameter("book_id"));
//
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("view-full-whole-book"))
                .forward(req, resp);
    }
}
