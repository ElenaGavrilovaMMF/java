package service;

import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redirector {
    private static final String showRedirectPage = "profile";

    public static void redirectShow(HttpServletRequest req, HttpServletResponse resp, BoxFolder rootFolder) throws ServletException, IOException {
        for (BoxItem.Info itemInfo : rootFolder) {
            System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
        }
        req.getRequestDispatcher(JspPathUtil.get(showRedirectPage))
                .forward(req, resp);
    }
}
