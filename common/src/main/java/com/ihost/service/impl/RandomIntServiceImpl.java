package com.test.service.impl;
import com.test.exception.RandomIntObjectNotFoundException;
import com.test.service.RandomIntService;
import com.test.model.RandomIntObject;
import com.test.repository.RandomIntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RandomIntServiceImpl implements RandomIntService {

    @Autowired
    private RandomIntRepository repository;

    @Override
    public RandomIntObject save(RandomIntObject obj) {
          return  repository.save(obj);
    }

    @Override
    public RandomIntObject get(String id) {
        return repository.findById(id).orElseThrow(()->new RandomIntObjectNotFoundException(String.format("object not found by id %s.",id)));
    }

}
