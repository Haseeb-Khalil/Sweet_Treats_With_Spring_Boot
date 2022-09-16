package com.sweetTreats.Sweet_Treats_With_Spring_Boot.repository;

import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
