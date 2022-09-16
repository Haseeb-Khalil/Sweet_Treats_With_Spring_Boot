package com.sweetTreats.Sweet_Treats_With_Spring_Boot.service;

import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Courier;
import com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity.Order;
import com.sweetTreats.Sweet_Treats_With_Spring_Boot.exception.CourierNotFoundException;
import com.sweetTreats.Sweet_Treats_With_Spring_Boot.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

@Service
public class CourierServiceImplementation implements CourierService {

    private static final Logger LOGGER = Logger.getLogger(CourierServiceImplementation.class.getName());

    static {
        FileHandler fileHandler = null;

        try {
            fileHandler = new FileHandler(CourierServiceImplementation.class.getSimpleName() + ".log");
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        LOGGER.addHandler(fileHandler);
    }

    @Autowired
    private CourierRepository courierRepository;

//    public boolean isCourierInformationValid(Courier courierDetails) {
//        if (courierDetails.getName().isEmpty() || courierDetails.getName().length() < 3 ||
//                !isValidTime(String.valueOf((courierDetails.getStartTime()))) ||
////                LocalTime.parse(courierDetails.getStartTime()).isBefore(LocalTime.parse("09:00", DateTimeFormatter.ofPattern("HH:mm"))) ||
////                LocalTime.parse(courierDetails.getEndTime()).isAfter(LocalTime.parse("17:00", DateTimeFormatter.ofPattern("HH:mm"))) ||
//                courierDetails.getStartTime() == null ||
//                courierDetails.getEndTime() == null ||
//                courierDetails.getPricePerMile() < 1 ||
//                courierDetails.getMaxDistance() < 1) {
//            return false;
//        } else {
//            return true;
//        }
//
//    }

    // Add a courier to couriers database
    @Override
    public Courier addCourier(Courier newCourier) {
//        if (isCourierInformationValid(newCourier)) {
            LOGGER.log(Level.INFO, "New Courier added: " + newCourier.getName());
            return courierRepository.save(newCourier);
//        } else {
//            throw new Exception("Correct Courier info needed");
//        }
    }

    // Get all Couriers
    @Override
    public List<Courier> getAllCouriers() {
        LOGGER.log(Level.INFO, "Getting All Couriers.");
        return (List<Courier>) courierRepository.findAll();
    }

    // Get a courier with an ID
    @Override
    public Courier findCourier(String id) throws Exception {
        Optional<Courier> courier = courierRepository.findById(id);
        if (courier.isPresent()) {
            LOGGER.log(Level.INFO, "Getting a Courier With ID: " + id);
            return courier.get();
        } else {
            throw new CourierNotFoundException("Courier not found with id: " + id);
        }

    }


    // Check if courier is available or not
    public boolean isAvailable(Order order, Courier courier) {
        return order.getOrderTime().isAfter(LocalTime.parse(courier.getStartTime())) && order.getOrderTime().isBefore(LocalTime.parse(courier.getEndTime())) && order.getDistance() <= courier.getMaxDistance() && order.isRefrigeratedBoxRequired() == courier.isHasRefrigeratedBox();
    }


    // Get the cheapest/best courier
    @Override
    public Courier getBestSuitableCourier(Order order) {
        List<Courier> couriers = (List<Courier>) courierRepository.findAll();
//        if (order.getDistance() <= 0) {
//            throw new CourierNotFoundException("Correct Distance is required");
//        }
//        if (order.getOrderTime().isBefore(LocalTime.parse("09:00", DateTimeFormatter.ofPattern("HH:mm"))) || order.getOrderTime().isAfter(LocalTime.parse("17:00", DateTimeFormatter.ofPattern("HH:mm")))) {
//            throw new CourierNotFoundException("Order time is outside of working hours");
//        }


        List<Courier> availableCouriers = couriers.stream().filter(courier -> isAvailable(order, courier)).collect(Collectors.toList()); // List of available couriers

        if (!availableCouriers.isEmpty()) {
            Comparator<Courier> comparator = Comparator.comparing(courier -> courier.getPricePerMile()); // Compares by price per mile
            Courier cheapestCourier = availableCouriers.stream().min(comparator).get(); // Gets the cheapest courier

            LOGGER.log(Level.INFO, "Best suitable courier for this order is: " + cheapestCourier.getName() +
                    "\n" + "Working Hours: " + cheapestCourier.getStartTime() + " - " + cheapestCourier.getEndTime() +
                    "\n" + "Has Refrigerated Box: " + cheapestCourier.isHasRefrigeratedBox() +
                    "\n" + "Price: £" + cheapestCourier.getPricePerMile() + " per mile" +
                    "\n" + "Total Delivery Price For This Order is: £" + cheapestCourier.getPricePerMile() * order.getDistance());
            return cheapestCourier;

        } else {
            LOGGER.log(Level.WARNING, "No suitable courier found for this order: " + "\n" + "Order Time: " + order.getOrderTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" + "Refrigerated Box Required: " + order.isRefrigeratedBoxRequired() + "\n" + "Distance Between Customer And Restaurant: " + order.getDistance() + " miles.");
            throw new CourierNotFoundException("No suitable courier found for this order: " + "\n" + "Order Time: " + order.getOrderTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" + "Refrigerated Box Required: " + order.isRefrigeratedBoxRequired() + "\n" + "Distance Between Customer And Restaurant: " + order.getDistance() + " miles.");

        }

    }

    // Update a courier's Details
    @Override
    public Courier updateCourierDetails(String id, Courier courierDetails) {
        try {
            Courier courier = courierRepository.findById(id).get();
            LOGGER.log(Level.INFO, "Updating Courier Details of Id: " + id + " Name: " + courier.getName() +
                    "\n" + "Changing" +
                    "\n" + "Name: " + courier.getName() + " to: " + courierDetails.getName() +
                    "\n" + "Working Hours: " + courier.getStartTime() + " - " + courier.getEndTime() + " to: " + courierDetails.getStartTime() + "-" + courierDetails.getEndTime() +
                    "\n" + "Has Refrigerated Box: " + courier.isHasRefrigeratedBox() + " to: " + courierDetails.isHasRefrigeratedBox() +
                    "\n" + "Price Per Mile: £" + courier.getPricePerMile() + " to: £" + courierDetails.getPricePerMile());

//            if (isCourierInformationValid(courierDetails)) {
                courier.setName(courierDetails.getName());
                courier.setStartTime(courierDetails.getStartTime());
                courier.setEndTime(courierDetails.getEndTime());
                courier.setMaxDistance(String.valueOf(courierDetails.getMaxDistance()));
                courier.setPricePerMile(courierDetails.getPricePerMile());
                courier.setHasRefrigeratedBox(courierDetails.isHasRefrigeratedBox());
//            }
            return courierRepository.save(courier);

        } catch (CourierNotFoundException courierNotFoundException) {
            LOGGER.log(Level.INFO, "Courier: " + id + " is not found.");
            throw new CourierNotFoundException(" No Courier Found with Id: " + id);
        }
//        catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }

    // Delete a courier
    @Override
    public void deleteCourier(String id) {
        Optional<Courier> courier = courierRepository.findById(id);
        if (courier.isPresent()) {
            LOGGER.log(Level.INFO, "Deleting Courier: " + id + ".");
            courierRepository.deleteById(id);
        } else {
            LOGGER.log(Level.INFO, "Courier: " + id + " is not found.");
            throw new CourierNotFoundException("Courier " + id + " not found");
        }
    }
}

