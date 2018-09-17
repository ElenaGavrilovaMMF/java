package library.servlet.chapter;


import library.service.ChapterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/chapterDelete", name = "ChapterDelete")
public class ChapterDeleteServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChapterService.getInstance().deleteChapter(Long.valueOf(req.getParameter("chapter_id")));
        resp.sendRedirect("/bookFullInfo?user_id=" + req.getParameter("user_id") + "&book_id=" + req.getParameter("book_id"));

    }
}
