package Model;

import java.io.IOException;
import java.util.ArrayList;

public class AdministratorList implements JsonList<Administrator> {
    JsonParser<Administrator> parser = new JsonParser<>("/Database/administrator.json", Administrator.class);
    private ArrayList<Administrator> administrators;

    public AdministratorList() {
        setList();
    }

    public ArrayList<Administrator> getAdministrators(){
        return administrators;
    }

    @Override
    public void save(){
        try {
            parser.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setList(){
        try {
            this.administrators = parser.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addItem(Administrator item) {
        this.administrators.add(item);
        try {
            parser.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Administrator getItem(String id) {
        for (int i = 0; i < administrators.size(); i++){
            Administrator tempAdministrator = administrators.get(i);
            if (tempAdministrator.getID().equals(id)){
                return tempAdministrator;
            }
        }
        return null;
    }

    @Override
    public int getSize(){
        return administrators.size();
    } 
}
