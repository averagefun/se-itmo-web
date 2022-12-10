package managers;

import entities.User;

import java.util.Optional;

@SuppressWarnings("ClassCanBeRecord")
public class MyUserManager implements UserManager {
    private final EntityManager entityManager;

    public MyUserManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(User user) {
        return entityManager.persist(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = entityManager.find(User.class, username);
        return Optional.ofNullable(user);
    }

    @Override
    public boolean checkIfUserExists(String username) {
        User user = entityManager.find(User.class, username);
        return user != null;
    }
}