package com.ihost.service;

import com.ihost.model.RandomIntObject;

public interface RandomIntService {

    RandomIntObject save(RandomIntObject obj);


    RandomIntObject get(String id);
}
