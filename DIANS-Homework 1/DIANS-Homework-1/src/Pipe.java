import java.util.ArrayList;
import java.util.List;

public class Pipe<T> {
    private List<Filter<T>> filters = new ArrayList<>();

    private String data;

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public T runFilter(T input) {

        this.data = "";
        for (Filter<T> filter : filters) {
            input = filter.execute(input);
            data = this.data.concat(filter.getData() + "\n");
        }
        return input;
    }

    public void empty(){
        filters = new ArrayList<>();
    }

    public List<Filter<T>> getFilters() {
        return filters;
    }

    public String getData() {
        return data;
    }
}
