package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JsonParser<T> {
    ArrayList<T> list;
    private Class<T> type;
    private String filePath;
    private File dataFile;

    public JsonParser(String filePath, Class<T> type){
        this.filePath = filePath;
        this.type = type;

        String basePath = new File("").getAbsolutePath();
        this.dataFile = new File(basePath, this.filePath);
    }

    public void serialize() throws StreamWriteException, DatabindException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(dataFile, list);;
    }
    
    public ArrayList<T> deserialize() throws StreamReadException, DatabindException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, this.type);
        this.list = mapper.readValue(dataFile, collectionType);
        return this.list;
    }
}
