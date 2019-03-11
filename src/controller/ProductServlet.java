package controller;

import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private static ArrayList<Product> products = contructor();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "create":
                createProducts(request, response);
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "create":
                forWard(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    public static ArrayList<Product> contructor() {
        ArrayList<Product> productss = new ArrayList<>();
        productss.add(new Product(1, "box", 65.4));
        productss.add(new Product(2, "rrrr", 434.5));
        productss.add(new Product(3, "gggg", 23.23));
        productss.add(new Product(4, "aaaa", 234.53));
        productss.add(new Product(5, "vvvv", 345.86));
        productss.add(new Product(6, "nnnnn", 34.98));
        return productss;
    }

    protected void listProducts(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("products/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void forWard(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("products/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void createProducts(HttpServletRequest request, HttpServletResponse response) {
        int id = products.size() + 1;
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        products.add(new Product(id, name, price));
        listProducts(request, response);
    }
}
