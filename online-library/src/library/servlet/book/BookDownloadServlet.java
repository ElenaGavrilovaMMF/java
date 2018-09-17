package library.servlet.book;

import library.dto.book.ViewBookFullInfoDto;
import library.service.BookService;
import library.util.WriterFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/bookDownload", name="BookDownload")
public class BookDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("book_id");
        ViewBookFullInfoDto bookFullInfoDto = BookService.getInstance().findById(Long.valueOf(id));
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition","attachment; filename="+bookFullInfoDto.getName()+".txt");
        String text = WriterFile.readUsingBufferedReaderText(BookService.getInstance().findByIdText(Long.valueOf(id)).getUrl());
        resp.getWriter().write(text);
    }
}
