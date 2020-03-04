package com.ihost.repository;

import com.ihost.model.RandomIntObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomIntRepository extends MongoRepository<RandomIntObject,String> {
}
