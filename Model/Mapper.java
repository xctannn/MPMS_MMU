package Model;

import com.fasterxml.jackson.databind.ObjectMapper;

// Singleton mapper object
// To get an instance of the mapper, invoke "Mapper.INSTANCE.getMapper()"
public enum Mapper {
    INSTANCE;

    private final ObjectMapper mapper;

    private Mapper(){
        this.mapper = create();
    }

    public ObjectMapper getMapper(){
        return this.mapper;
    }

    private static ObjectMapper create(){
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }
}
