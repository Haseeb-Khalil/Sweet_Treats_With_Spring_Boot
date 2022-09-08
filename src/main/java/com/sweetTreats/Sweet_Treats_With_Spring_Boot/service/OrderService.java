package com.sweetTreats.Sweet_Treats_With_Spring_Boot.service;

import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Order;


public interface OrderService {
    Order makeOrder(String time, boolean refrigerator, double distance);

}
