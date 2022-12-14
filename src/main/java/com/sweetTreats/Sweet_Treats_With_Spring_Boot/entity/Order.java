package com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Entity
@Document("order")
public class Order {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotNull(message = "Can not be empty(Format required HH:mm)")
    private LocalTime orderTime;
    @NotNull(message = "Can not be empty boolean required")
    @NotEmpty(message = "Refrigeration  requirement is needed")
    private boolean isRefrigeratedBoxRequired;
    @NotNull(message = "Cannot be less than one or empty")
    @Min(value = 1, message = "Correct distance is needed")
    private double distance;

    // Constructor for Order class.
    public Order(String orderTime, boolean hasRefrigeratedBox, double distance) {
        if (orderTime == null || orderTime == "" || !isValidTime(orderTime)) {
            throw new IllegalArgumentException("Invalid order time");
//            System.out.println("You entered invalid Order time format: " + orderTime + "." + " Required Format (HH:mm)");
//            this.orderTime = LocalTime.now();
//            System.out.println("Using current time of your device. Order time: " + this.orderTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        } else {
            this.orderTime = LocalTime.parse(orderTime, DateTimeFormatter.ofPattern("HH:mm"));
        }
        this.isRefrigeratedBoxRequired = hasRefrigeratedBox;
        this.distance = distance;
    }

    // Getters and Setters
    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public boolean isRefrigeratedBoxRequired() {
        return isRefrigeratedBoxRequired;
    }

    public void setRefrigeratedBoxRequired(boolean refrigeratedBoxRequired) {
        isRefrigeratedBoxRequired = refrigeratedBoxRequired;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    // Check if time is in HH:mm format
    public static boolean isValidTime(String time) {
        // Regex to check valid time in 24-hour format.
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the time is empty
        // return false
        if (time == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given time
        // and regular expression.
        Matcher m = p.matcher(time);

        // Return if the time
        // matched the ReGex
        return m.matches();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderTime=" + orderTime +
                ", isRefrigeratedBoxRequired=" + isRefrigeratedBoxRequired +
                ", distance=" + distance +
                '}';
    }
}

