package library.servlet.user;


import library.dto.user.CreateNewUserDto;
import library.service.UserService;
import library.util.JspPathUtil;
import library.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/createUser", name = "CreateUser")
public class UserCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  req.setAttribute("users", UserService.getInstance().findAll());
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("create-user"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String passwordFirst = req.getParameter("passwordFirst");
        String passwordSecond = req.getParameter("passwordSecond");
        CreateNewUserDto userDto = CreateNewUserDto.builder()
                .login(req.getParameter("login"))
                .password(req.getParameter("passwordFirst"))
                .name(req.getParameter("name"))
                .build();
        List<String> validateResult = UserValidator.getInstance().validate(userDto);
        if(!passwordFirst.equals(passwordSecond)){
            validateResult.add("Перепроверьте пароли!");
        }
        if(passwordFirst.equals(passwordSecond) &&validateResult.isEmpty()){
            UserService.getInstance().save(userDto);
            resp.sendRedirect("/saveUser");
        }else {
            req.setAttribute("errors", validateResult);
            req.setAttribute("login", req.getParameter("login"));
            req.setAttribute("passwordFirst", passwordFirst);
            req.setAttribute("passwordSecond",passwordSecond);
            req.setAttribute("name",req.getParameter("name"));


            getServletContext()
                    .getRequestDispatcher(JspPathUtil.get("create-user"))
                    .forward(req, resp);

        }
    }
}
