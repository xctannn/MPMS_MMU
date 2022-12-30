package Model;

public  interface JsonList<T> {
    public void addItem(T item);

    public T getItem(String id);
}