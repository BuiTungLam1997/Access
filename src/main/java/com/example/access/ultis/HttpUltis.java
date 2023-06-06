package com.example.access.ultis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;

public class HttpUltis {
    private String value;

    public HttpUltis(String value) {
        this.value = value;
    }
    public <T> T toModel(Class<T> tClass){
        try {
            return  new ObjectMapper().readValue(value,tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static HttpUltis of(BufferedReader reader) {
        StringBuilder json = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HttpUltis(json.toString());
    }
}
