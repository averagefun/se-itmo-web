package beans;

import database.ResultDao;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResultBean implements Serializable {
    private static final int[] X_VALUES = {-5, -4, -3, -2, -1, 0, 1, 2, 3};
    private static final double MIN_Y = -5;
    private static final double MAX_Y = 5;

    @Inject
    private ResultDao resultDaoImpl;

    private Result currResult;
    private List<Result> resultList;

    @PostConstruct
    private void initialize() {
        currResult = new Result();
        resultList = new ArrayList<>();
//        resultList = resultDaoImpl.getAll();
    }

    public void addResult() {
        Result copyResult = new Result(currResult);
        resultList.add(copyResult);
        resultDaoImpl.save(copyResult);
    }
}
