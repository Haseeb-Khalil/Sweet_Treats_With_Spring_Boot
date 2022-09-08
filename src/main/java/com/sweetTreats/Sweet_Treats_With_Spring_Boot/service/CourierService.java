package com.sweetTreats.Sweet_Treats_With_Spring_Boot.service;

import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Courier;
import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Order;

import java.util.List;

public interface CourierService {
    Courier addCourier(Courier newCourier) throws Exception;

    List<Courier> getAllCouriers();

    Courier findCourier(long id) throws Exception;

    Courier getBestSuitableCourier(Order order) throws Exception;

    Courier updateCourierDetails(Long id, Courier courierDetails) throws Exception;

    void deleteCourier(Long id);

    boolean isCourierInformationValid(Courier courierDetails) throws Exception;
}
