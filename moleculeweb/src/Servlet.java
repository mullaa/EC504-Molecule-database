import java.io.*;
import javax.servlet.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String text = request.getParameter("inputfile");
        response.getWriter().println(text);
//        newDatabase test = new newDatabase();
//        test.addMolecule (text);
        addmolecule test = new addmolecule();
        test.getTextValue(text);
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
