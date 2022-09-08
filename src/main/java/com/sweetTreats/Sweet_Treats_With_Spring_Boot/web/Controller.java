package com.sweetTreats.Sweet_Treats_With_Spring_Boot.web;

import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Courier;
import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Order;
import com.sweetTreats.Sweet_Treats_With_Spring_Boot.exception.CourierNotFoundException;
import com.sweetTreats.Sweet_Treats_With_Spring_Boot.service.CourierService;
import com.sweetTreats.Sweet_Treats_With_Spring_Boot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sweet_treats")
public class Controller {
    private CourierService courierService;
    private OrderService order;

    @Autowired
    public void setCourierService(CourierService courierService) {
        this.courierService = courierService;
    }

    @Autowired
    public void setOrder(OrderService order) {
        this.order = order;
    }

    @RequestMapping(path = "/add_courier", method = RequestMethod.POST)
    public ResponseEntity<Courier> addCourier(@RequestBody Courier courier) throws Exception {
        if (courierService.isCourierInformationValid(courier)) {
            Courier newCourier = courierService.addCourier(courier);
            return new ResponseEntity<Courier>(newCourier, HttpStatus.CREATED);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Correct Courier info needed");
        }

    }

    @RequestMapping(path = "/couriers")
    public ResponseEntity<List<Courier>> getAllCouriers() {
        List<Courier> couriers = courierService.getAllCouriers();
        return new ResponseEntity<List<Courier>>(couriers, HttpStatus.OK);

    }

    @RequestMapping(path = "/courier/{id}")
    public ResponseEntity<Courier> getCourier(@PathVariable("id") long id) throws Exception {
        try {
            return new ResponseEntity<Courier>(courierService.findCourier(id), HttpStatus.OK);
        } catch (CourierNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Courier " + id + " not found");
        }

    }

    @RequestMapping(path = "/courier/{time}/{distance}/{refrigerator}")
    public ResponseEntity<Courier> getBestCourier(@PathVariable("time") String time, @PathVariable("refrigerator") boolean refrigerator, @PathVariable("distance") double distance) throws Exception {
        if (!Order.isValidTime(time) || distance <= 0 || (!refrigerator && refrigerator)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Correct Time and Distance are required.");
        }
        Order makeOrder = order.makeOrder(time, refrigerator, distance);
//        Order makeOrder = new Order(time, refrigerator, distance);
        try {
            return new ResponseEntity<Courier>(courierService.getBestSuitableCourier(makeOrder), HttpStatus.OK);
        } catch (CourierNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No courier available for this order");
        }

    }

    @RequestMapping(path = "/update_courier/{courier_id}", method = RequestMethod.PUT)
    public ResponseEntity<Courier> updateCourier(@PathVariable("courier_id") Long id, @RequestBody Courier courierDetails) throws Exception {

        try {
            new ResponseEntity<Courier>(courierService.findCourier(id), HttpStatus.OK);
        } catch (CourierNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Courier " + id + " not found");
        }

        if (!courierService.isCourierInformationValid(courierDetails)) {
            throw new CourierNotFoundException("Appropriate Courier Name & Working Hours are required.");
        }
        return new ResponseEntity<Courier>(courierService.updateCourierDetails(id, courierDetails), HttpStatus.OK);

    }

    @RequestMapping(path = "/delete_courier/{courier_id}", method = RequestMethod.DELETE)
    public String deleteCourier(@PathVariable("courier_id") Long id) {
        try {
            courierService.deleteCourier(id);
            return "Courier: " + id + " deleted";
        } catch (CourierNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Courier " + id + " not found");
        }
    }
}
