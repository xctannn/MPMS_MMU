package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

/*
 * Purpose: A generic class that can be implemented to serialize and deserialize a JSON list, or a JSON object
 * Design Pattern: Singleton, only a single instance of objectMapper will be created, despite being constructed twice
 */
public class JsonParser<T> {
    ArrayList<T> list;
    T object;
    private Class<T> type;
    private String filePath;
    private File dataFile;

    /*
     * Tan Xiao Chin
     * Constructor: 
     * filePath = path to the related json file
     * type = class of the data in the json file
     * 
     * Purpose: Finds the file object based on the filePath
     */
    public JsonParser(String filePath, Class<T> type){
        this.filePath = filePath;
        this.type = type;

        String basePath = new File("").getAbsolutePath();
        this.dataFile = new File(basePath, this.filePath);
    }

    /*
     * Tan Xiao Chin
     * Purpose: Converts ArrayList into a JSON file
     */
    public void serialize() throws StreamWriteException, DatabindException, IOException{
        ObjectMapper mapper = Mapper.INSTANCE.getMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(dataFile, list);
    }

    /*
     * Tan Xiao Chin
     * Purpose: Converts an object into a JSON file
     */
    public void serializeObject() throws StreamWriteException, DatabindException, IOException{
        ObjectMapper mapper = Mapper.INSTANCE.getMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(dataFile, object);
    }
    
    /*
     * Tan Xiao Chin
     * Purpose: Parses a JSON file and returns an ArrayList
     */
    public ArrayList<T> deserialize() throws StreamReadException, DatabindException, IOException{
        ObjectMapper mapper = Mapper.INSTANCE.getMapper();
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, this.type);
        this.list = mapper.readValue(dataFile, collectionType);
        return list;
    }

    /*
     * Tan Xiao Chin
     * Purpose: Parses a JSON file and returns an object
     */
    public T deserializeObject() throws StreamReadException, DatabindException, IOException{
        ObjectMapper mapper = Mapper.INSTANCE.getMapper();
        this.object = mapper.readValue(dataFile, type);
        return object;
    }
}
