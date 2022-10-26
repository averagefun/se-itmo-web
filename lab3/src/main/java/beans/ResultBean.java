package beans;

import database.ResultDao;
import lombok.Data;

import javax.inject.Inject;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
public class ResultBean implements Serializable {
    private static final int[] X_VALUES = {-5, -4, -3, -2, -1, 0, 1, 2, 3};
    private static final double MIN_Y = -5;
    private static final double MAX_Y = 5;

    @Inject
    private ResultDao resultDao;

    private Result currResult;
    private List<Result> resultList;

    @PostConstruct
    private void initialize() {
        currResult = new Result();
        updateLocal();
    }

    private void updateLocal() {
        resultList = resultDao.getAll();
    }

    public void addResult() {
        Result copyResult = new Result(currResult);
        resultDao.save(copyResult);
        updateLocal();
    }

    public void clearResults() {
        resultDao.clear();
        resultList = resultDao.getAll();
        updateLocal();
    }
}
