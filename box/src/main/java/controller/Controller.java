package controller;

import action.LoginBoxAction;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import config.ConfigBox;
import service.Redirector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        BoxFolder rootFolder = BoxFolder.getRootFolder(getAPIClient(req, resp));
        Redirector.redirectShow(req, resp, rootFolder);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setCharacterEncoding("utf-8");
        res.sendRedirect(new LoginBoxAction().getBoxRedirect());
    }

    private BoxAPIConnection getAPIClient(HttpServletRequest req, HttpServletResponse resp) {
        String code = req.getParameter("code");
        return new BoxAPIConnection(ConfigBox.client_id, ConfigBox.client_secret, code);
    }
}
