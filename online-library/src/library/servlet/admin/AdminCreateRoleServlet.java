package library.servlet.admin;

import library.dto.book.CreateNewBookDto;
import library.dto.book.ViewBookBasicInfoDto;
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
@WebServlet(value = "/removeRole", name = "RemoveRole")
public class AdminCreateRoleServlet extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("genres", GenreService.getInstance().findAll());
//        req.setAttribute("user_id", req.getParameter("user_id"));
//        getServletContext()
//                .getRequestDispatcher(JspPathUtil.get("profile-admin"))
//                .forward(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleUserDto roleUserDto = new RoleUserDto(Long.valueOf(req.getParameter("user")), Integer.valueOf(req.getParameter("role")));
        RoleService.getInstance().save(roleUserDto);
        LoginUserDto user = UserService.getInstance().findByIdUser(Long.valueOf(req.getParameter("user")));
        User userFull = UserService.getInstance().findByLogin(req.getParameter("user"));

        req.setAttribute("user", userFull);
        if(user.getRole().equals(RoleService.getInstance().findById(Integer.parseInt(req.getParameter("role"))).getName())){
            req.setAttribute("result","Операция проведена успешно!");
            getServletContext()
                    .getRequestDispatcher(JspPathUtil.get("profile-admin"))
                    .forward(req, resp);
        }else {
            req.setAttribute("result","Ошибка записи!");
            getServletContext()
                    .getRequestDispatcher(JspPathUtil.get("profile-admin"))
                    .forward(req, resp);
        }

    }


}
