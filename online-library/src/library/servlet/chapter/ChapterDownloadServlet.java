package library.servlet.chapter;

import library.dto.book.ViewBookFullInfoDto;
import library.dto.chapter.ViewChapterFullInfoDto;
import library.service.BookService;
import library.service.ChapterService;
import library.util.WriterFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/chapterDownload", name="ChapterDownload")
public class ChapterDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("chapter_id");
        ViewChapterFullInfoDto chapter = ChapterService.getInstance().findById(Long.valueOf(id));
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition","attachment; filename="+chapter.getName()+".txt");
        String text = WriterFile.readUsingBufferedReaderText(ChapterService.getInstance().findById(Long.valueOf(req.getParameter("chapter_id"))).getUrl());
        resp.getWriter().write(text);
    }
}
