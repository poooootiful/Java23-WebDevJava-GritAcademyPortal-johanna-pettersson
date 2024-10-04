package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

public class SettersGetters {
    static String State = "Anonymous";
    static String UserType = "User";
    static int UserId = 0;
    public static List<Integer> CoursesIds = new ArrayList<>();


    public static String getState () {
        return State;
    }
    public static void setState (String state) {
        State = state;
    }

    public static String getUserType () {
        return UserType;
    }
    public static void setUserType (String userType) {
        UserType = userType;
    }

    public static int getUserId () {
        return UserId;
    }
    public static void setUserId (int id) {
        UserId = id;
    }
}
