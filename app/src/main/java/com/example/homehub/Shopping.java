package com.example.homehub;

import java.io.Serializable;

public class Shopping implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shopping(String name){
        this.name = name;
    }
}
