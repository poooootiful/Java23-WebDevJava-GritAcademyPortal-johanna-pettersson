package Servlets;

import Models.SettersGetters;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/UserPage")
public class UserPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>UserPage</title></head>");
        out.println("<body style=\"background-color:Gray;\">");
        out.println("<h2>UserPage</h2>");
        out.println("<ul>\n" +
                "       <li style=\"display:inline\"><a href=\"/Home\">Home</a></li>\n" +
                "       <li style=\"display:inline\"><a href=\"/Courses\">Courses</a></li>\n");
        if (SettersGetters.getState().matches("Confirmed")) {
            out.println ("<li style=\"display:inline\"><a href=\"/Logout\">Logout</a></li>\n");
        } else {
            out.println ("<li style=\"display:inline\"><a href=\"/Login\">Login</a></li>\n");
        }
        out.println("<li style=\"display:inline\"><a href=\"/UserPage\">UserPage</a></li>\n" +
                "    </ul>");

        if (SettersGetters.getState().matches("Confirmed")) {
            out.println("<ul>\n" +
                    "       <li style=\"display:inline\"><a href=\"/Teacher\">Teacher</a></li>\n" +
                    "       <li style=\"display:inline\"><a href=\"/Student\">Student</a></li>\n" +
                    "    </ul>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
