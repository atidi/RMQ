package com.test.repository;

import com.test.model.RandomIntObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomIntRepository extends MongoRepository<RandomIntObject,String> {
}
