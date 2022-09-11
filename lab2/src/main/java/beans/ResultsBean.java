package beans;

import java.util.ArrayList;
import java.util.List;

public class ResultsBean {
    private List<Result> results = new ArrayList<>();

    public ResultsBean() {
    }

    public ResultsBean(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}