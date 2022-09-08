package com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime orderTime;

    private boolean isRefrigeratedBoxRequired;

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

