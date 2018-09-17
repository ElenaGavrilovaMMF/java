package library.servlet.page;


import library.dao.BookDao;
import library.dto.book.ViewBookFullInfoDto;
import library.dto.user.LoginUserDto;
import library.entity.User;
import library.service.BookService;
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

@WebServlet("/page")
public class PageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("page",BookService.getInstance().findAllPage(Integer.parseInt(req.getParameter("numberPage"))-1));
        req.setAttribute("allpages",BookService.getInstance().countPageAll(BookDao.getInstance().countBook()));
        req.setAttribute("numberPage",req.getParameter("numberPage"));
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("login"))
                .forward(req, resp);
    }

}
