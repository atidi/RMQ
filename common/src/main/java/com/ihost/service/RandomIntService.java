package com.test.service;

import com.test.model.RandomIntObject;

public interface RandomIntService {

    RandomIntObject save(RandomIntObject obj);


    RandomIntObject get(String id);
}
