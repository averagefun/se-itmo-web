package managers;

public interface EntityManager {
    boolean persist(Object obj);

    <T> T find(Class<T> clazz, String key);
}

