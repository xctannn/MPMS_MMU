package src.Model;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Purpose: map objects or arraylist to JSON file
 * Design Pattern: Singleton, ensures that only a single instance of ObjectMapper is created, reducing space complexity
 */
public enum Mapper {
    INSTANCE;

    private final ObjectMapper mapper;

    /*
     * Tan Xiao Chin
     * Constructor
     */
    private Mapper(){
        this.mapper = create();
    }

    /*
     * Tan Xiao Chin
     * Purpose: Return the ObjectMapper
     */
    public ObjectMapper getMapper(){
        return mapper;
    }

    /*
     * Tan Xiao Chin
     * Purpose: Instantiate a ObjectMapper object named mapper, and returns it
     */
    private static ObjectMapper create(){
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }
}
