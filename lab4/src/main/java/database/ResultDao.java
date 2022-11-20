package database;

import beans.Result;

import java.util.List;

public interface ResultDao {
    void save(Result result);

    boolean clear();

    List<Result> getAll();
}
