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

@WebServlet(urlPatterns = "/Courses")
public class Courses extends HttpServlet {
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

        //Table for courses
        out.println("<table style = \"border: 1px solid\">\n" +
                    "  <tr>\n" +
                    "    <th style = \"border: 1px solid\">Id</th>\n" +
                    "    <th style = \"border: 1px solid\">Name</th>\n" +
                    "    <th style = \"border: 1px solid\">Yhp</th>\n" +
                    "    <th style = \"border: 1px solid\">Description</th>\n" +
                    "  </tr>\n");

        //Database Connection
        Connection con = DatabaseConnect.getConnection();
        String ShowCourses = "SELECT*FROM courses";

        //Get info from database and put it in the table
        try {
            Statement statement = con.createStatement();

            ResultSet result = statement.executeQuery(ShowCourses);

            while (result.next()) {
                out.println(("  <tr>\n" +
                        "    <td style = \"border: 1px solid\">" + result.getInt("id") + "</td>\n" +
                        "    <td style = \"border: 1px solid\">" + result.getString("Name") + "</td>\n" +
                        "    <td style = \"border: 1px solid\">" + result.getString("YHP") + "</td>\n" +
                        "    <td style = \"border: 1px solid\">" + result.getString("Description") + "</td>\n" +
                        "  </tr>\n"));
            }
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        //Closing tags for html
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
