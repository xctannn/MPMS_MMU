package src.Model;

/*
 * Purpose: An interface for all the model lists that parses the JSON files
 */
public interface JsonList<T>{
    /*
     * Tan Xiao Chin
     * Purpose: Populate ArrayList with all the file's data
     */
    public void setList(); 

    /*
     * Tan Xiao Chin
     * Purpose: Save the list to the json file
     */
    public void save();

    /*
     * Tan Xiao Chin
     * Purpose: Add new items to the ArrayList
     */
    // 
    public void addItem(T item);

    /*
     * Tan Xiao Chin
     * Purpose: Get specified item from ArrayList based on the item's id 
     */
    public T getItem(String id);

    /*
     * Tan Xiao Chin
     * Purpose: Get amount of items that are in the list
     */
    public int getSize();
}