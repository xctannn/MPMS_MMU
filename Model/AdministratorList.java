package Model;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Chua Hui Yi
 * Purpose: Implemented the JsonList to the class to manipulate the list of admin objects in Administrator.json
 */
public class AdministratorList implements JsonList<Administrator> {
    JsonParser<Administrator> parser = new JsonParser<>("/Database/administrator.json", Administrator.class);
    private ArrayList<Administrator> administrators;

    /* 
     * Chua Hui Yi
     * No-Arg Constructor
     */
    public AdministratorList() {
        setList();
    }

    /* 
     * Chua Hui Yi
     * Purpose: Return the arraylist of administrators
     */
    public ArrayList<Administrator> getAdministrators(){
        return administrators;
    }

    /*
     * Chua Hui Yi
     * Purpose: Generate the user id
     */
    public String generateCode(int listSize){
        return String.format("%04d", listSize + 1);
    }

    /* 
     * Chua Hui Yi
     * Purpose: Save the administrator into Administrator.json
     */
    @Override
    public void save(){
        try {
            parser.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 
     * Chua Hui Yi
     * Purpose: Reads the admin and populates data into the AdministratorList
     */
    @Override
    public void setList(){
        try {
            this.administrators = parser.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 
     * Chua Hui Yi
     * Purpose: Add new admin object into the arraylist Administrator
     */
    @Override
    public void addItem(Administrator item) {
        setList();
        this.administrators.add(item);     
        save();
    }

    /* 
     * Chua Hui Yi
     * Purpose: To get the admin object based on the id
     */
    @Override
    public Administrator getItem(String id) {
        for (int i = 0; i < administrators.size(); i++){
            Administrator tempAdministrator = administrators.get(i);
            if (tempAdministrator.getId().equals(id)){
                return tempAdministrator;
            }
        }
        return null;
    }

    /* 
     * Chua Hui Yi
     * Purpose: To get the size of the arraylist
     */
    @Override
    public int getSize(){
        ArrayList<Administrator> tempList;
        try {
            tempList = parser.deserialize();
            return tempList.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    } 
}
