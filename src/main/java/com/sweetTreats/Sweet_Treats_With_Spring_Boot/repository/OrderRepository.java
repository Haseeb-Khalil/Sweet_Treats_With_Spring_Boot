package com.sweetTreats.Sweet_Treats_With_Spring_Boot.repository;

import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
