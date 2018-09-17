package library.servlet.chapter;


import library.dto.book.ViewBookFullInfoDto;
import library.dto.chapter.CreateNewChapterDto;
import library.service.BookService;
import library.service.ChapterService;
import library.service.GenreService;
import library.util.JspPathUtil;
import library.util.WriterFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/createChapter", name = "Create")
public class ChapterCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("book_id");
        req.setAttribute("book_id", id);
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("create-chapter"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("book_id");
        String text = req.getParameter("text");
        String nameFile = "/"+id+req.getParameter("name")+".txt";
        WriterFile.writeUsingFiles(text,nameFile);

        CreateNewChapterDto chapterDto = CreateNewChapterDto.builder()
                .name(req.getParameter("name"))
                .url(WriterFile.FILE_PATH+nameFile)
                .bookId(Long.valueOf(id))//Книгу поменять
                .build();

            ChapterService.getInstance().save(chapterDto);
            resp.sendRedirect("/saveUser");
    }


}
