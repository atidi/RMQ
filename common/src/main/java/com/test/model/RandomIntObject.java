package com.test.model;


import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


public class RandomIntObject implements Serializable {

    private String id = UUID.randomUUID().toString();

   private List<Integer> randomInt;

    public RandomIntObject() {
    }

    public RandomIntObject(List<Integer> randomInt) {
        this.randomInt = randomInt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getRandomInt() {
        return randomInt;
    }

    public void setRandomInt(List<Integer> randomInt) {
        this.randomInt = randomInt;
    }
}
