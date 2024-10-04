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
import java.util.Set;

@WebServlet(urlPatterns = "/Teacher")
public class Teacher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>UserPage</title></head>");
        out.println("<body style=\"background-color:Gray;\">");
        out.println("<h2>UserPage</h2>");
        out.println("<ul>\n" +
                        "            <li style=\"display:inline\"><a href=\"/Home\">Home</a></li>\n" +
                "       <li style=\"display:inline\"><a href=\"/Courses\">Courses</a></li>\n");
        if (SettersGetters.getState().matches("Confirmed")) {
            out.println ("<li style=\"display:inline\"><a href=\"/Logout\">Logout</a></li>\n");
        } else {
            out.println ("<li style=\"display:inline\"><a href=\"/Login\">Login</a></li>\n");
        }
         out.println("<li style=\"display:inline\"><a href=\"/UserPage\">UserPage</a></li>\n" +
                "    </ul>");
        out.println("<ul>\n" +
                "       <li style=\"display:inline\"><a href=\"/Teacher\">Teacher</a></li>\n" +
                "       <li style=\"display:inline\"><a href=\"/Student\">Student</a></li>\n" +
                "    </ul>");

        //For testing teacher page without login in
        //SettersGetters.setUserType("teacher");
        String usertype = SettersGetters.getUserType();

        if (usertype.matches("teacher")) {

            //Start of table 1
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

            //Closing tags for courses table
            out.println("</table>");
        }
        if (usertype.matches("teacher")) {
            //Start of table 2
            out.println("<table style = \"border: 1px solid\">\n" +
                    "  <tr>\n" +
                    "    <th style = \"border: 1px solid\">Id</th>\n" +
                    "    <th style = \"border: 1px solid\">Fname</th>\n" +
                    "    <th style = \"border: 1px solid\">Lname</th>\n" +
                    "    <th style = \"border: 1px solid\">Town</th>\n" +
                    "    <th style = \"border: 1px solid\">Email</th>\n" +
                    "    <th style = \"border: 1px solid\">Phone</th>\n" +
                    "    <th style = \"border: 1px solid\">Username</th>\n" +
                    "    <th style = \"border: 1px solid\">Password</th>\n" +
                    "  </tr>\n");
            //Database Connection2
            Connection con2 = DatabaseConnect.getConnection();
            String ShowStudents = "SELECT*FROM students";

            //Get info from database and put it in the table
            try {
                Statement statement = con2.createStatement();

                ResultSet result = statement.executeQuery(ShowStudents);

                while (result.next()) {
                    out.println(("  <tr>\n" +
                            "    <td style = \"border: 1px solid\">" + result.getInt("id") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + result.getString("Fname") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + result.getString("Lname") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + result.getString("Town") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + result.getString("email") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + result.getString("phone") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + result.getString("username") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + result.getString("password") + "</td>\n" +
                            "  </tr>\n"));
                }
                con2.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);

            }

            //Closing tag for student table
            out.println("</table>");

        }
        out.println("</body>");
        out.println("</html>");
    }
}
