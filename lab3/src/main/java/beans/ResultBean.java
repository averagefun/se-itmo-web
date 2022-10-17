package beans;

import lombok.Data;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResultBean implements Serializable {
    private static final int[] X_VALUES = {-5, -4, -3, -2, -1, 0, 1, 2, 3};
    private static final double MIN_Y = -5;
    private static final double MAX_Y = 5;

    private Result currResult;
    private List<Result> resultList;

    @PostConstruct
    private void initialize() {
        resultList = new ArrayList<>();
    }

    public void addResult() {
        resultList.add(currResult);
    }
}
