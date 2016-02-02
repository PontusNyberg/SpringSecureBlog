package com.example.backend;

import java.util.HashMap;

import com.google.gson.Gson;

import spark.Response;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
    
    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        if (model instanceof Response) {
            return gson.toJson(new HashMap<>());
        }
        String data = gson.toJson(model);
        return data;
    }

}
