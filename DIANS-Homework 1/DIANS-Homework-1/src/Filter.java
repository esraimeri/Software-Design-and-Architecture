public interface Filter<T> {

    T execute(T input);

    String getData();

    void clearData();

}
