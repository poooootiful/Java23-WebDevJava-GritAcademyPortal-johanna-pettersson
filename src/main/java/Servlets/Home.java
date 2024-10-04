package Servlets;

import Models.DatabaseConnect;
import Models.SettersGetters;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/Home")
public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Courses</title></head>");
        out.println("<body style=\"background-color:Gray;\">");
        out.println("<h2>Courses</h2>");
        //Navigation
        out.println("<ul>\n" +
                "            <li style=\"display:inline\"><a href=\"/Home\">Home</a></li>\n" +
                "            <li style=\"display:inline\"><a href=\"/Courses\">Courses</a></li>\n");
        if (SettersGetters.getState().matches("Confirmed")) {
            out.println ("<li style=\"display:inline\"><a href=\"/Logout\">Logout</a></li>\n");
        } else {
            out.println ("<li style=\"display:inline\"><a href=\"/Login\">Login</a></li>\n");
        }
        out.println("<li style=\"display:inline\"><a href=\"/UserPage\">UserPage</a></li>\n" +
                "        </ul>");

        //Closing tags for html
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
