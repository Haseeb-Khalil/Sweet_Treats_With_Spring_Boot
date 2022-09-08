package com.sweetTreats.Sweet_Treats_With_Spring_Boot.repository;

import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Courier;
import org.springframework.data.repository.CrudRepository;

public interface CourierRepository extends CrudRepository<Courier, Long> {
}
