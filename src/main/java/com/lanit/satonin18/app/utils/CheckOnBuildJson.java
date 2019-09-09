package com.lanit.satonin18.app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CheckOnBuildJson {

    public void check(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(obj);
        System.err.println("JSON: " + str);
    }

}
