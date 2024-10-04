package Servlets;

import Models.Functions;
import Models.SettersGetters;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/Logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String state = SettersGetters.getState();

        if (state.matches("Confirmed")) {
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head><title>Login</title></head>");
            out.println("<body style=\"background-color:Gray;\">");
            out.println("<h2>Login</h2>");
            out.println("<ul>\n" +
                    "            <li style=\"display:inline\"><a href=\"/Home\">Home</a></li>\n" +
                    "            <li style=\"display:inline\"><a href=\"/Courses\">Courses</a></li>\n" +
                    "            <li style=\"display:inline\"><a href=\"/Login\"></a>Login</li>\n" +
                    "            <li style=\"display:inline\"><a href=\"/UserPage\">UserPage</a></li>\n" +
                    "        </ul>");
            out.println("<form>\n" +
                    "        <input type=\"submit\" value=\"logout\" name=\"Logout\">" +
                    "    </form>");
            //Html end tags
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
            SettersGetters.setUserType("");
            SettersGetters.setState("Anonymous");
            SettersGetters.setUserId(0);
    }
}
