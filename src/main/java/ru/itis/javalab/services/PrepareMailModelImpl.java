package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

public class PrepareMailModelImpl implements PrepareMailModel {

    @Autowired
    private Environment environment;

    @Override
    public Map prepareMailModel(String code, String name) {
        Map model = new HashMap();
        model.put("name", name);
        model.put("location", "Kazan");
        model.put("link", "http://localhost:8080/confirm/" + code);
        return model;
    }
}
