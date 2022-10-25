package database;

import beans.Result;
import javax.transaction.Transactional;
import javax.enterprise.inject.Default;
import javax.persistence.*;
import java.util.List;

@Default
public class ResultDaoImpl implements ResultDao {
    @PersistenceContext(name = "ResultUnit")
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Result result) {
        entityManager.persist(result);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void clear() {
        entityManager.createQuery("delete from Result").executeUpdate();
    }

    public List<Result> getAll() {
        return entityManager.createQuery("select", Result.class).getResultList();
    }
}
