package library.servlet.ban;

import library.dao.BanDao;
import library.dto.ban.CreateBanDto;
import library.dto.ban.ViewBanDto;
import library.dto.book.CreateNewBookDto;
import library.dto.book.ViewBookBasicInfoDto;
import library.dto.book.ViewBookFullInfoDto;
import library.dto.bookmark.CreateNewBookmarkDto;
import library.dto.user.LoginUserDto;
import library.dto.user.RoleUserDto;
import library.dto.user.ViewUserBasicInfoDto;
import library.entity.Role;
import library.entity.User;
import library.service.*;
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
@WebServlet(value = "/ban", name = "Ban")
public class AdminBanServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BanService.getInstance().save(new CreateBanDto(Long.valueOf(req.getParameter("book_id"))));
        resp.sendRedirect("/bookFullInfo?user_id="+req.getParameter("user_id")+"&book_id="+req.getParameter("book_id"));



    }


}
