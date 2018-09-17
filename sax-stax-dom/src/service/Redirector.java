package service;

import entity.Flower;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Redirector {
    private static final String showRedirectPage = "showUploadedPage.jsp";
    private static final int pageSize = 4;

    public static void redirectShow(HttpServletRequest req, HttpServletResponse resp, Set<Flower> flowers) throws ServletException, IOException {

        ArrayList<Flower> flowerList = new ArrayList<>(flowers);
        req.getSession().setAttribute("entities", flowerList);

        List<Integer> pages = getPagesList(flowerList);

        List<Flower> tempList = getTempList(flowerList, 1);

        req.setAttribute("pageNumber", 1);
        req.setAttribute("entities", tempList);
        req.setAttribute("pages", pages);

        req.getRequestDispatcher(showRedirectPage).forward(req, resp);
    }

    public static void redirectShow(HttpServletRequest req, HttpServletResponse resp, int page) throws ServletException, IOException {

        ArrayList<Flower> flowerList = (ArrayList<Flower>) req.getSession().getAttribute("entities");

        List<Integer> pages = getPagesList(flowerList);

        List<Flower> tempList = getTempList(flowerList, page);

        req.setAttribute("pageNumber", page);
        req.setAttribute("entities", tempList);
        req.setAttribute("pages", pages);

        req.getRequestDispatcher(showRedirectPage).forward(req, resp);
    }

    private static List<Integer> getPagesList(ArrayList<Flower> flowerList) {
        List<Integer> pages = new ArrayList<>();
        int pagesNumber = (int) Math.ceil(flowerList.size() / pageSize);
        for (int i = 0; i < pagesNumber; i++) {
            pages.add(i + 1);
        }
        return pages;
    }

    private static List<Flower> getTempList(ArrayList<Flower> flowerList, int page) {
        List<Flower> tempList = new ArrayList<>();
        for (int i = (page - 1) * pageSize; i < (page) * pageSize; i++) {
            tempList.add(flowerList.get(i));
        }
        return tempList;
    }

}
