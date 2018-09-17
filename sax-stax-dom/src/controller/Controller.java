package controller;

import dao.ValidatorXML;
import entity.Flower;
import service.AbstractFlowerBuilder;
import service.FlowerBuilderFactory;
import service.Redirector;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@MultipartConfig
@WebServlet("/")
public class Controller extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("command");
        if ("getPage".equals(action)) {
            Redirector.redirectShow(request, response, Integer.parseInt(request.getParameter("number")));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        FlowerBuilderFactory fFactory = new FlowerBuilderFactory();
        AbstractFlowerBuilder builder = fFactory.createFlowerBuilder(request.getParameter("command"));
        String path = getServletContext().getRealPath("/data/");
        if (ValidatorXML.validatorXML(path + "flower.xml", path + "flower.xsd")) {
            builder.buildSetFlowers(path + "flower.xml");

            Set<Flower> cards = builder.getFlowers();
            Redirector.redirectShow(request, response, cards);
        }

    }

}
