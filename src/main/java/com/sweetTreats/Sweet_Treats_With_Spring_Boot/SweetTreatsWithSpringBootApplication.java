package com.sweetTreats.Sweet_Treats_With_Spring_Boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SweetTreatsWithSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SweetTreatsWithSpringBootApplication.class, args);
        System.out.println("App Running With Spring Boot");
    }

//    @Bean
//    public CommandLineRunner demo(CourierRepository courierRepository) {
//        return (args) -> {
//            courierRepository.save(new Courier("Bobby", 5.0, 1.75, "09:00", "13:00", true));
//            courierRepository.save(new Courier("Martin", 3.0, 1.50, "09:00", "17:00", false));
//            courierRepository.save(new Courier("Geoff", 4.0, 2.00, "10:00", "16:00", true));
//            courierRepository.save(new Courier("John", 3.0, 1.25, "10:00", "16:00", true));
//
//            for (Courier courier : courierRepository.findAll()) {
//                System.out.println("Courier is:" + courier.toString());
//            }
//        };
//    }

}
