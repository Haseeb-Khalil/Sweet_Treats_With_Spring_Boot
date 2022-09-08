package com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double maxDistance;
    private double pricePerMile;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean hasRefrigeratedBox;


    public Courier() {
    }

    // Constructor for Courier class.
    public Courier(String name, double maxDistance, double pricePerMile, String startTime, String endTime, boolean hasRefrigeratedBox) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.pricePerMile = pricePerMile;
        this.startTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
        this.endTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"));
        this.hasRefrigeratedBox = hasRefrigeratedBox;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(String maxDistance) {
        this.maxDistance = Double.parseDouble(maxDistance);
    }

    public double getPricePerMile() {
        return pricePerMile;
    }

    public void setPricePerMile(double pricePerMile) {
        this.pricePerMile = pricePerMile;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isHasRefrigeratedBox() {
        return hasRefrigeratedBox;
    }

    public void setHasRefrigeratedBox(boolean hasRefrigeratedBox) {
        this.hasRefrigeratedBox = hasRefrigeratedBox;
    }


    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxDistance=" + maxDistance +
                ", pricePerMile=" + pricePerMile +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", hasRefrigeratedBox=" + hasRefrigeratedBox +
                '}';
    }

}

