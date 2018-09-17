package library.servlet.user;


import library.dao.BookDao;
import library.dto.book.ViewBookFullInfoDto;
import library.dto.user.LoginUserDto;
import library.entity.User;
import library.service.BookService;
import library.service.UserService;
import library.util.JspPathUtil;
import library.util.PageUtil;
import library.util.StringUtil;
import library.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("page",BookService.getInstance().findAllPage(0));
        req.setAttribute("allpages",BookService.getInstance().countPageAll(BookDao.getInstance().countBook()));
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = UserService.getInstance().findByLogin(login);
        List<ViewBookFullInfoDto> numberPage = BookService.getInstance().findAllPage(0);
        req.setAttribute("page",numberPage);
        req.setAttribute("allpages",BookService.getInstance().countPageAll(BookDao.getInstance().countBook()));
        List<String> errors = new ArrayList<>();
        if(user!=null){
            if(password.equals(user.getPassword())){
                req.getSession().setAttribute("currentUser", new LoginUserDto(user.getId(), login,user.getRole().getName()));
                resp.sendRedirect("/booksList?user_id="+user.getId());
            }else {
                errors.add("Проверьте логин или пароль!");
                req.setAttribute("errors",errors);
                getServletContext()
                        .getRequestDispatcher(JspPathUtil.get("login"))
                        .forward(req, resp);
            }
        }else {
            errors.add("Проверьте логин или пароль!");
            req.setAttribute("errors",errors);
            getServletContext()
                    .getRequestDispatcher(JspPathUtil.get("login"))
                    .forward(req, resp);
        }
    }
}
