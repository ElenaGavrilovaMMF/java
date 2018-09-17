package library.servlet.ban;


import library.service.BanService;
import library.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/banUn", name = "BanFullInf")
public class BanUnServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BanService.getInstance().deleteBan(Long.valueOf(req.getParameter("book_id")));
        resp.sendRedirect("/bookFullInfo?user_id=" + req.getParameter("user_id") + "&book_id=" + req.getParameter("book_id"));

    }
}

