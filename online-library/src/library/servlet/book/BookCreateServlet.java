package library.servlet.book;


import library.dto.book.CreateNewBookDto;
import library.dto.book.ViewBookBasicInfoDto;
import library.dto.user.CreateNewUserDto;
import library.service.BookService;
import library.service.GenreService;
import library.service.UserService;
import library.util.JspPathUtil;
import library.util.WriterFile;
import library.validator.UserValidator;
import sun.util.resources.LocaleData;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@MultipartConfig
@WebServlet(value = "/createBook", name = "CreateBook")
public class BookCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("genres", GenreService.getInstance().findAll());
        req.setAttribute("user_id", req.getParameter("user_id"));
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("create-book"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        String bookWhole = req.getParameter("bookWhole");
        if (!req.getParameter("name").equals("") && Long.valueOf(req.getParameter("genre")) !=0) {
            if (bookWhole.equals("BookWholeFalse")) {
                CreateNewBookDto bookDto = CreateNewBookDto.builder()
                        .name(req.getParameter("name"))
                        .idUser(Long.valueOf(req.getParameter("user_id")))
                        .bookWhole(Boolean.FALSE)
                        .description(req.getParameter("description"))
                        .url(WriterFile.FILE_PATH)
                        .genreId(Integer.valueOf(req.getParameter("genre")))
                        .build();
                ViewBookBasicInfoDto book = BookService.getInstance().save(bookDto);
                resp.sendRedirect("/bookFullInfo?user_id=" + req.getParameter("user_id") + "&book_id=" + book.getId());

            } else if (bookWhole.equals("BookWholeTrue")) {
                String nameFile = "/"+req.getParameter("user_id")+req.getParameter("name")+".txt";
                Part filePart = req.getPart("file");
                String collect = new BufferedReader(new InputStreamReader(filePart.getInputStream()))
                        .lines().collect(Collectors.joining(System.lineSeparator()));
                WriterFile.writeUsingFiles(collect, nameFile);
                CreateNewBookDto bookDto = CreateNewBookDto.builder()
                        .name(req.getParameter("name"))
                        .idUser(Long.valueOf(req.getParameter("user_id")))
                        .bookWhole(Boolean.TRUE)
                        .description(req.getParameter("description"))
                        .url(WriterFile.FILE_PATH + nameFile)
                        .genreId(Integer.valueOf(req.getParameter("genre")))
                        .build();
                ViewBookBasicInfoDto book = BookService.getInstance().save(bookDto);
                resp.sendRedirect("/bookFullInfo?user_id=" + req.getParameter("user_id") + "&book_id=" + book.getId());

            }else {
                errors.add("Проверьте правильность заполненных данных");
                req.setAttribute("errors",errors);
                req.setAttribute("genres", GenreService.getInstance().findAll());
                req.setAttribute("name", req.getParameter("name"));
                req.setAttribute("description",req.getParameter("description"));
                req.setAttribute("bookWhole",bookWhole);
            //    req.setAttribute("user_id", req.getParameter("user_id"));
                getServletContext()
                        .getRequestDispatcher(JspPathUtil.get("create-book"))
                        .forward(req, resp);
            }
        }else {
            errors.add("Проверьте правильность заполненных данных");
            req.setAttribute("genres", GenreService.getInstance().findAll());
            req.setAttribute("name", req.getParameter("name"));
            req.setAttribute("description",req.getParameter("description"));
            req.setAttribute("bookWhole",bookWhole);
            req.setAttribute("errors",errors);
        //    req.setAttribute("user_id", req.getParameter("user_id"));
            getServletContext()
                    .getRequestDispatcher(JspPathUtil.get("create-book"))
                    .forward(req, resp);
        }
    }


}
