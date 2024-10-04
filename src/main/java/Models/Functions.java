package Models;

import com.sun.tools.classfile.Opcode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Functions {
    public static int getTeacherId (String username){
        int teacherId = 0;
        Connection connection = DatabaseConnect.getConnection();
        String sql="SELECT id FROM teachers WHERE Username = '"+username+"';";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                teacherId = resultSet.getInt("id");

            } else if (!resultSet.next()) {
                return 0;
            }
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SettersGetters.setUserId(teacherId);
        return teacherId;
    }

    public static int getStudentId (String username){
        int studentId = 0;
        Connection connection = DatabaseConnect.getConnection();
        String sql = "SELECT id FROM students WHERE Username = '" + username + "';";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                studentId = resultSet.getInt("id");
            }
            /*else if (!resultSet.next()) {
                return 0;
            }*/
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SettersGetters.setUserId(studentId);
        return studentId;
    }
}
