package com.sweetTreats.Sweet_Treats_With_Spring_Boot.exception;

public class CourierNotFoundException extends RuntimeException {
    public CourierNotFoundException(String exception) {
        super(exception);
    }
}
