package library.servlet.user;


import library.dto.book.ViewBookFullInfoDto;
import library.entity.Role;
import library.entity.User;
import library.service.BookService;
import library.service.GenreService;
import library.service.RoleService;
import library.service.UserService;
import library.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/userScearch", name = "UserScearch")
public class UserSearchServlet extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("genres", GenreService.getInstance().findAll());
//  //      req.setAttribute("user_id",req.getParameter("user_id"));
//        List<ViewBookFullInfoDto> books = BookService.getInstance().findAllUser();
//        req.setAttribute("books", books);
//        getServletContext()
//                .getRequestDispatcher(JspPathUtil.get("search-book"))
//                .forward(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserService.getInstance().findByLogin(req.getParameter("user"));
        if(user==null){
            req.setAttribute("result2", "Ничего не найдено");
        }else if(user.getLogin().equals(req.getParameter("user"))){
            req.setAttribute("user", user);
        }
        List<Role> allRole = RoleService.getInstance().findAll();
        List<Role> allRoleUser = new ArrayList<>();
        for (Role role : allRole) {
            if(!(role.getName()).equals("Администратор")){
                allRoleUser.add(role);
            }
        }

        req.setAttribute("roles",allRoleUser);
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("profile-admin"))
                .forward(req, resp);

    }

}
