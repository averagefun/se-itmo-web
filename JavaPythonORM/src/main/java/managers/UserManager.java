package managers;

import entities.User;
import java.util.Optional;

public interface UserManager {
    public boolean save(User user);

    public Optional<User> findByUsername(String username);

    public boolean checkIfUserExists(String username);
}
