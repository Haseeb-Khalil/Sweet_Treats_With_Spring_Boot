package com.sweetTreats.Sweet_Treats_With_Spring_Boot.service;

import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Courier;
import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Order;

import java.util.List;

public interface CourierService {
    Courier addCourier(Courier newCourier);

    List<Courier> getAllCouriers();

    Courier findCourier(String id) throws Exception;

    Courier getBestSuitableCourier(Order order) throws Exception;

    Courier updateCourierDetails(String id, Courier courierDetails) throws Exception;

    void deleteCourier(String id);

//    boolean isCourierInformationValid(Courier courierDetails) throws Exception;
}
