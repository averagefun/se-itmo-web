import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import fastcgi.client.FCGIClient;

import managers.*;
import entities.User;

public class Main {
    public static String HOST = "127.0.0.1";
    public static int PORT = 42085;
    public static int TIMEOUT = 3000;

    public static void main(String[] args) {
        FCGIClient client = new FCGIClient(HOST, PORT, false, TIMEOUT);

        EntityManager entityManager = new MyEntityManager(client);
        UserManager myUserManager = new MyUserManager(entityManager);

//        User user = new User("Michel", "cats");
//        System.out.println(myUserManager.save(user) ? "Successfully saved!" : "Saving failed!");

//        System.out.println(myUserManager.checkIfUserExists("Michel"));

        Optional<User> user = myUserManager.findByUsername("Michel");
        System.out.println(user.isPresent() ? user.get() : "User not found!");
    }
}
