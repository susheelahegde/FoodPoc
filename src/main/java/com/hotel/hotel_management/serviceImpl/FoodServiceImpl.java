/**
 *
 */
package com.hotel.hotel_management.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotel.hotel_management.entity.FoodEntity;
import com.hotel.hotel_management.entity.HotelEntity;
import com.hotel.hotel_management.repository.FoodRepository;
import com.hotel.hotel_management.repository.HotelRepository;
import com.hotel.hotel_management.service.FoodService;

import ch.qos.logback.classic.Logger;

@Component
public class FoodServiceImpl implements FoodService{

	@Autowired
	private FoodRepository foodRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public FoodEntity addFood(FoodEntity foodEntity) {
		if(foodEntity.getHotel_id() == null) {
			//Error needs to handle
			System.out.println("Hotel id is mandatory to add food");
			return null;
		}

		foodEntity.setFood_id(UUID.randomUUID().toString());

		if(this.hotelRepository.exists(foodEntity.getHotel_id())) {   //checking hotel existence
			//Listing all food in an hotel by hotel id and comparing new food names with existing list of foods
			List<FoodEntity> foodMenu = this.foodRepository.findFoodByHotel(foodEntity.getHotel_id());
			for (FoodEntity food : foodMenu) {
				if(food.getFood_name().equalsIgnoreCase(foodEntity.getFood_name())) {
					System.out.println("ohhhh!!!! Already food exists.");
					return null;
				}
			}

//		if(this.hotelRepository.exists(foodEntity.getHotel_id())) {   //checking hotel existence
//			if (foodEntity.getFood_id() != null) {
//				foodEntity.setFood_id(UUID.randomUUID().toString());
//	        }
//	        System.out.println("Food entity: "+foodEntity.toString());
//	        this.foodRepository.save(foodEntity);
		} else {
			//Error needs to handle
			System.out.println("Hotel not food");
			return null;
		}

		return foodEntity;
	}

	@Override
	public FoodEntity updateFood(FoodEntity foodEntity) {
		try {
			if (foodEntity.getHotel_id() == null) {
				// Error needs to handle
				System.out.println("Hotel id is mandatory to update food details");
				return null;
			}
			if (this.hotelRepository.exists(foodEntity.getHotel_id())) { // checking hotel existence
				//FoodEntity existingFood = new FoodEntity();
				this.foodRepository.exists(foodEntity.getFood_id());
				if (this.foodRepository.exists(foodEntity.getFood_id())) {
					this.foodRepository.update(foodEntity.getFood_name(), foodEntity.getFood_price(),
							foodEntity.getHotel_id(), foodEntity.getFood_id());
					System.out.println("updated food details successfully");
				} else {
					System.out.println("Food not found");
					return null;
				}
			} else {
				// Error needs to handle
				System.out.println("Hotel not food");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Error occured during updating food : "+e);
		}
		return foodEntity;
	}

	@Override
	public void deleteFood(String id) {
		FoodEntity foodEntity = this.foodRepository.findOne(id);
        if (foodEntity != null) {
        		System.out.println(this.foodRepository.findFoodByHotel(id));
            this.foodRepository.delete(id);
        }
	}

	@Override
	public List<FoodEntity> getFoodByFilters(String hotelName) {
		List<FoodEntity> foodList = new ArrayList<FoodEntity>();
		List<HotelEntity> hotel = new ArrayList<HotelEntity>();
		try {
			if(hotelName != null) {
				hotel = this.hotelRepository.findHotelByHotelName(hotelName);
				System.out.println("Hotel ====== "+hotel);
				for (HotelEntity hotelEntity : hotel) {
					foodList.addAll(this.foodRepository.findHotelByfilters(hotelEntity.getId()));
					//foodList = this.foodRepository.findHotelByfilters(hotelEntity.getId());
					System.out.println("food List ; "+foodList);
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Error occured during search food by hotel name : "+e.getMessage());
		}
		return foodList;
	}



}
