package com.ihost.service.impl;
import com.ihost.exception.RandomIntObjectNotFoundException;
import com.ihost.service.RandomIntService;
import com.ihost.model.RandomIntObject;
import com.ihost.repository.RandomIntRepository;
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
