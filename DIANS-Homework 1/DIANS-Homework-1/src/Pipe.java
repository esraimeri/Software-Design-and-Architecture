import java.util.ArrayList;
import java.util.List;

public class Pipe<T> {
    private List<Filter<T>> filters = new ArrayList<>();

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public T runFilter(T input) {

        for (Filter<T> filter : filters) {
            input = filter.execute(input);
        }
        return input;
    }
}
