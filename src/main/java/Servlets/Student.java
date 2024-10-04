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
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/Student")
public class Student extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>StudentPage</title></head>");
        out.println("<body style=\"background-color:Gray;\">");
        out.println("<h2>StudentPage</h2>");
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

        String usertype = SettersGetters.getUserType();
        //The student courses by id
        if (usertype.matches("student")) {
            out.println("<div>");
            out.println("<p>The courses you are part off</p>");
            out.println("<table style = \"border: 1px solid\">\n" +
                    "  <tr>\n" +
                    "    <th style = \"border: 1px solid\">CourseIds</th>\n" +
                    "  </tr>\n");


            int id = SettersGetters.getUserId();
            out.println("<p>Your id is:"+id+"</p>");

            String sql = "SELECT CourseId FROM studentcourses WHERE StudentID = "+id+";";
            Connection connection = DatabaseConnect.getConnection();

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    out.println(("  <tr>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getInt("CourseId") + "</td>\n" +
                            "</tr>\n"));
                }
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);

            }

            out.println("</table>");
            out.println("</div>");

        }
        //All courses
        if (usertype.matches("student")) {
            out.println("<div>");
            out.println("<p>All Courses</p>");
            out.println("<table style = \"border: 1px solid\">\n" +
                        "  <tr>\n" +
                        "    <th style = \"border: 1px solid\">CourseId</th>\n" +
                        "    <th style = \"border: 1px solid\">Name</th>\n" +
                        "    <th style = \"border: 1px solid\">Yhp</th>\n" +
                        "    <th style = \"border: 1px solid\">Description</th>\n" +
                        "  </tr>\n");

            String sql = "SELECT * FROM courses;";
            Connection connection = DatabaseConnect.getConnection();

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    out.println(("  <tr>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getInt("id") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getString("Name") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getString("YHP") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getString("Description") + "</td>\n" +
                            "</tr>\n"));
                }

                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            out.println("</table>");
            out.println("</div>");
        }
        //Students id, fname & lname
        if (usertype.matches("student")) {
            out.println("<div>");
            out.println("<p>All student</p>");
            out.println("<table style = \"border: 1px solid\">\n" +
                    "  <tr>\n" +
                    "    <th style = \"border: 1px solid\">id</th>\n" +
                    "    <th style = \"border: 1px solid\">Fname</th>\n" +
                    "    <th style = \"border: 1px solid\">Lname</th>\n" +
                    "  </tr>\n");

            String sql = "SELECT id, Fname, Lname FROM students;";
            Connection connection = DatabaseConnect.getConnection();

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    out.println(("  <tr>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getInt("id") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getString("Fname") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getString("Lname") + "</td>\n" +
                            "</tr>\n"));
                }

                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            out.println("</table>");
            out.println("</div>");
        }
        //Student id connection to courses
        if (usertype.matches("student")) {
            out.println("<div>");
            out.println("<p>All students connections to courses</p>");
            out.println("<table style = \"border: 1px solid\">\n" +
                    "  <tr>\n" +
                    "    <th style = \"border: 1px solid\">CourseId</th>\n" +
                    "    <th style = \"border: 1px solid\">StudentId</th>\n" +
                    "  </tr>\n");

            String sql = "SELECT * FROM studentcourses;";
            Connection connection = DatabaseConnect.getConnection();

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    out.println(("  <tr>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getInt("CourseId") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getInt("StudentId") + "</td>\n" +
                            "</tr>\n"));
                }
                connection.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            out.println("</table>");
            out.println("</div>");
        }
        //Teacher id, fname & lname
        if (usertype.matches("student")) {
            out.println("<div>");
            out.println("<p>All teachers</p>");
            out.println("<table style = \"border: 1px solid\">\n" +
                    "  <tr>\n" +
                    "    <th style = \"border: 1px solid\">id</th>\n" +
                    "    <th style = \"border: 1px solid\">Fname</th>\n" +
                    "    <th style = \"border: 1px solid\">Lname</th>\n" +
                    "  </tr>\n");

            String sql = "SELECT id, Fname, Lname FROM teachers;";
            Connection connection = DatabaseConnect.getConnection();

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    out.println(("  <tr>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getInt("id") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getString("Fname") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getString("Lname") + "</td>\n" +
                            "</tr>\n"));
                }

                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            out.println("</table>");
            out.println("</div>");
        }
        //Teacher id connection to courses
        if (usertype.matches("student")) {
            out.println("<div>");
            out.println("<p>All teacher connections to courses</p>");
            out.println("<table style = \"border: 1px solid\">\n" +
                    "  <tr>\n" +
                    "    <th style = \"border: 1px solid\">CourseId</th>\n" +
                    "    <th style = \"border: 1px solid\">TeacherId</th>\n" +
                    "  </tr>\n");

            String sql = "SELECT * FROM teachercourses;";
            Connection connection = DatabaseConnect.getConnection();

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    out.println(("  <tr>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getInt("CourseId") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getInt("TeacherId") + "</td>\n" +
                            "</tr>\n"));
                }

                connection.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            out.println("</table>");
            out.println("</div>");
        }

    }
}