/**
 * 
 */
package com.hotel.hotel_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.hotel_management.entity.FoodEntity;

@Service
public interface FoodService {
	
	public FoodEntity addFood(FoodEntity foodEntity);

	public FoodEntity updateFood(FoodEntity foodEntity);

	public void deleteFood(String id);

	public List<FoodEntity> getFoodByFilters(String hotelName);
	
}
