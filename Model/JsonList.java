package Model;

import java.util.ArrayList;

public  interface JsonList<T> {
    public void setList();

    public void addItem(T item);

    public T getItem(String id);
}