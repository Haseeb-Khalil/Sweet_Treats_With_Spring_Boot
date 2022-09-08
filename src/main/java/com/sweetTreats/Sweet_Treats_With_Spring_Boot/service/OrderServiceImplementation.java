package com.sweetTreats.Sweet_Treats_With_Spring_Boot.service;

import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Order;
import com.sweetTreats.Sweet_Treats_With_Spring_Boot.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImplementation implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order makeOrder(String time, boolean refrigerator, double distance) {
        return new Order(time, refrigerator, distance);
    }
}
