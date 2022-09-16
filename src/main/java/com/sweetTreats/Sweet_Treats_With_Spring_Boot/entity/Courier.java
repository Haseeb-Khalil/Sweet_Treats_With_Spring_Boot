package com.sweetTreats.Sweet_Treats_With_Spring_Boot.entity;


import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//@Entity
@Document(collection = "couriers")
public class Courier {
    //    private static final AtomicInteger count = new AtomicInteger(0);

    @Id
    private String id;
    @NotNull(message = "Cannot be less than three or empty")
    @Length(min = 3, message = "Correct name is required")
    private String name;
    @NotNull(message = "Cannot be less than one or empty")
    @Min(value = 1, message = "Correct Distance is required")
    private double maxDistance;
    @NotNull(message = "Cannot be less than one or empty")
    @Min(value = 1, message = "Correct price per mile is required")
    private double pricePerMile;
    @NotNull(message = "Can not be empty(Format required HH:mm)")
    private String startTime; // Changed to String to deal with database issue in MongoDb
    @NotNull(message = "Can not be empty(Format required HH:mm)")
    private String endTime; // Changed to String to deal with database issue in MongoDb
    @NotNull(message = "Can not be empty boolean required")
    private boolean hasRefrigeratedBox;


    public Courier() {
    }

    // Constructor for Courier class.
    public Courier(String name, double maxDistance, double pricePerMile, String startTime, String endTime, boolean hasRefrigeratedBox) {
        this.id = id;
        this.name = name;
        this.maxDistance = maxDistance;
        this.pricePerMile = pricePerMile;
        this.startTime = startTime;
        this.endTime = endTime;
//        this.startTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
//        this.endTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"));
        this.hasRefrigeratedBox = hasRefrigeratedBox;
    }

//    public BigInteger idGenerator() {
//        BigInteger previous = getId();
//        BigInteger one = new BigInteger(String.valueOf(1));
//        return previous.add(one);
//    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

