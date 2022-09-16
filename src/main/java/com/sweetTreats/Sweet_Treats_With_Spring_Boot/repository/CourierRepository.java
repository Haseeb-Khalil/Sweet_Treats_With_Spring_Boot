package com.sweetTreats.Sweet_Treats_With_Spring_Boot.repository;

import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Courier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface CourierRepository extends MongoRepository<Courier, String> {
}
