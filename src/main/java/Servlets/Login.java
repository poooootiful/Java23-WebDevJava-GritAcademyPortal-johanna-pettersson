package Servlets;

import Models.DatabaseConnect;
import Models.Functions;
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

@WebServlet(urlPatterns = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Login</title></head>");
        out.println("<body style=\"background-color:Gray;\">");
        out.println("<h2>Login</h2>");
        out.println("<ul>\n" +
                "            <li style=\"display:inline\"><a href=\"/Home\">Home</a></li>\n" +
                "            <li style=\"display:inline\"><a href=\"/Courses\">Courses</a></li>\n");
        if (SettersGetters.getState().matches("Confirmed")) {
            out.println ("<li style=\"display:inline\"><a href=\"/Logout\">Logout</a></li>\n");
        } else {
            out.println ("<li style=\"display:inline\"><a href=\"/Login\">Login</a></li>\n");
        }
        out.println("<li style=\"display:inline\"><a href=\"/UserPage\">UserPage</a></li>\n" +
                    "</ul>");
        out.println("<form name=\"Login\" method=\"post\" action=\"Login\">\n" +
                "            <label>Username</label><br>\n" +
                "            <input type=\"text\" name=\"username\"><br>\n" +
                "            <label>Password</label><br>\n" +
                "            <input type=\"password\" name=\"password\"><br>\n" +
                            "<select name=\"usertype\">\n" +
                    "           <option value=\"teacher\">Teacher</option>\n" +
                    "           <option value=\"student\">Student</option>\n" +
                    "        </select>"+
                "            <input type=\"submit\" value=\"LogIn\">\n" +
                "        </form>");
        //Html end tags
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String username = req.getParameter("username");
        //String password = req.getParameter("password");
        String usertype = req.getParameter("usertype");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Login</title></head>");
        out.println("<body style=\"background-color:Gray;\">");
        out.println("<h2>Login</h2>");
        out.println("<ul>\n" +
                "            <li style=\"display:inline\"><a href=\"index.html\">Index</a></li>\n\n" +
                "            <li style=\"display:inline\"><a href=\"/Courses\">Courses</a></li>\n" +
                "            <li style=\"display:inline\"><a href=\"/Logout\">Logout</a></li>\n" +
                "            <li style=\"display:inline\"><a href=\"/UserPage\">UserPage</a></li>\n" +
                "        </ul>");
        out.println("<p>"+usertype+"</p>");
        out.println("<p>"+username+"</p>");

        if (usertype.matches("teacher")) {
            int userid = Functions.getTeacherId(username);
            //Connection connection = DatabaseConnect.getConnection();

                if (userid>0) {
                    SettersGetters.setState("Confirmed");
                    SettersGetters.setUserType(usertype);
                    out.println("<html>");
                    out.println("<head><title>Login</title></head>");
                    out.println("<body>");
                    out.println("<h2>You are logged in with usertype:" + usertype + "</h2>");

                } else if (userid==0) {
                    out.println("<p>Login Failed</p>");
                }
            out.println("</body>");
            out.println("</html>");

        } else if (usertype.matches("student")) {
            int userid = Functions.getStudentId(username);
            //Connection connection = DatabaseConnect.getConnection();
                if (userid > 0) {
                    SettersGetters.setState("Confirmed");
                    SettersGetters.setUserType(usertype);
                    out.println("<html>");
                    out.println("<head><title>Login</title></head>");
                    out.println("<body>");
                    out.println("<h2>You are logged in with usertype:" + usertype + "</h2>");




                } else if (userid==0) {
                    out.println("<p>Login Failed</p>");
                }

            out.println("</body>");
            out.println("</html>");
        }
    }
}
