package com.hotel.hotel_management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.hotel_management.entity.FoodEntity;
import com.hotel.hotel_management.entity.HotelEntity;
import com.hotel.hotel_management.service.FoodService;
import com.hotel.hotel_management.service.HotelService;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {
	
    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private FoodService foodService;

    /*========================================Hotel Apis===========================================================*/
    
    //Add hotel
    @RequestMapping(value = "/add_hotel", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<HotelEntity> saveHotel(@RequestBody HotelEntity hotelEntity) {     
        return ResponseEntity.ok().body(hotelService.save(hotelEntity));
    }
    
    //Delete Hotel
    @DeleteMapping(path = "/delete_hotel/{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable("hotelId") String hotelId) {
        this.hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }
    
    //Edit or update hotel
    @PutMapping(value = "/update_hotel", produces = "application/json")
    public ResponseEntity<HotelEntity> updateHotel(@RequestBody HotelEntity hotelEntity){
		
    		return ResponseEntity.ok().body(hotelService.updateHotel(hotelEntity));
    	
    }
    
    //Search hotel by filters
    @GetMapping(value = "/search_hotel", produces="application/json")
	public ResponseEntity<List<HotelEntity>> getHotelsByFilters(
			@RequestParam(value = "hotelName", required = false) String hotelName,
			@RequestParam(value = "location", required = false) String location) {
		System.out.println("inside search : " + hotelName + " location " + location);
		return ResponseEntity.ok().body(hotelService.getHotelsByFilters(hotelName, location));

	}
    
    /*========================================Food Apis===========================================================*/
    
    //Add food to hotel using hotel_id
    @PostMapping(value = "/add_food", produces = "application/json")
    public ResponseEntity<FoodEntity> addFood(@RequestBody FoodEntity foodEntity) {
		
    		return ResponseEntity.ok().body(foodService.addFood(foodEntity));
    	
    }
    
    //Edit or update food using food_id and hotel_id
    @PutMapping(value = "/update_food", produces = "application/json")
    public ResponseEntity<FoodEntity> updateHotel(@RequestBody FoodEntity foodEntity){
		
    		return ResponseEntity.ok().body(foodService.updateFood(foodEntity));
    	
    }
    
    //Delete food 
    @DeleteMapping(path = "/delete/food/{foodId}")
    public ResponseEntity<String> deleteFood(@PathVariable("foodId") String foodId) {
        this.foodService.deleteFood(foodId);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }
    
  //Search hotel by filters
    @GetMapping(value = "/search_food", produces="application/json")
	public ResponseEntity<List<FoodEntity>> getFoodByFilters(
			@RequestParam(value = "hotelName") String hotelName) {
		System.out.println("inside search : " + hotelName);
		return ResponseEntity.ok().body(foodService.getFoodByFilters(hotelName));

	}
    
    
}
