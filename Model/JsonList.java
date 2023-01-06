package Model;

public  interface JsonList<T>{
    // populates ArrayList with all the file's data
    public void setList(); 

    // saves the list to the json file
    public void save();

    // adds new items to the ArrayList
    public void addItem(T item);

    // get specified item from ArrayList by the item's id 
    public T getItem(String id);

    // get the amount of items that are in the list
    public int getSize();
}