package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultList implements Iterable<Result> {
    private final List<Result> results;

    public ResultList() {
         this.results = new ArrayList<>();
    }

    public void addNewResult(Result newResult) {
        results.add(newResult);
    }

    public void clear() {
        results.clear();
    }

    @Override
    public Iterator<Result> iterator() {
        // return results.iterator();
        return new Iterator<Result>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < results.size();
            }

            @Override
            public Result next() {
                return results.get(i++);
            }
        };
    }
}