package com.hotel.hotel_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hotel.hotel_management")
public class HotelManagementApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HotelManagementApplication.class, args);
		System.out.println("Sushiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
	}
}