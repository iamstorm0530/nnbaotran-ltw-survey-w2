package murach.data;

import java.util.ArrayList;
import java.util.List;
import murach.business.User;

public class UserDB {
    private static final List<User> USERS = new ArrayList<>();

    public static void insert(User user) {
        if (user != null) {
            USERS.add(user);
            System.out.println("Inserted user: " + user.getEmail());
        }
    }

    public static List<User> selectAll() {
        return new ArrayList<>(USERS);
    }
}
