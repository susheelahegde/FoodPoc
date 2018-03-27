package com.hotel.hotel_management.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotel.hotel_management.entity.FoodEntity;
import com.hotel.hotel_management.entity.HotelEntity;
import com.hotel.hotel_management.repository.FoodRepository;
import com.hotel.hotel_management.repository.HotelRepository;
import com.hotel.hotel_management.service.HotelService;

@Component
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    
    @Autowired
    private FoodRepository foodRepository;

	@Override
	public HotelEntity save(HotelEntity hotelEntity) {
        //HotelEntity hotel = new HotelEntity();
        if (hotelEntity.getId() != null) {
            hotelEntity.setId(UUID.randomUUID().toString());
        }
        System.out.println("Hotel entity: "+hotelEntity.toString());
        this.hotelRepository.save(hotelEntity);
        return hotelEntity;
    }

	@Override
	public HotelEntity updateHotel(HotelEntity hotelEntity) {
		if(this.hotelRepository.exists(hotelEntity.getId())) {
			this.hotelRepository.update(hotelEntity.getHotel_name(), 
					hotelEntity.getHotel_location(), hotelEntity.getContact_number(), hotelEntity.getId());
			System.out.println("updated hotel successfully");
		} else {
			System.out.println("hotel Not found");
			return null;
		}
		return hotelEntity;
	}

	@Override
	public void deleteHotel(String id) {
		List<FoodEntity> foodList = new ArrayList<>();
		String foodID;
		try {
			if (this.hotelRepository.exists(id)) {
				System.out.println("Inside delete hotel ");
				foodList = this.foodRepository.findFoodByHotel(id);
				for (FoodEntity foodEntity : foodList) {
					System.out.println("inside for loop : ----- : "+foodEntity);
					foodID = foodEntity.getFood_id();
					this.foodRepository.delete(foodID);
				}
	        		//this.foodRepository.deleteFoodByHotelId(id);
	            this.hotelRepository.delete(id);
	            System.out.println("Leaving delete hotel ");
	        }
		} catch (Exception e) {
			System.out.println("An error occured while deleting hotel and its food items : "+ e.getMessage());
		}
        
		
	}

	@Override
	public List<HotelEntity> getHotelsByFilters(String hotelName, String location) {
		List<HotelEntity> hotelsList = new ArrayList<HotelEntity>();
		try {
			if(hotelName != null && location != null) {	
				hotelsList = this.hotelRepository.findHotelByfilters(location, hotelName);
			} else if (hotelName != null) {
				hotelsList =  this.hotelRepository.findHotelByHotelName(hotelName);
			} else if(location != null) {
				hotelsList = this.hotelRepository.findHotelByLocation(location);
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Error occured during search hotel : "+e.getMessage());
		}
		return hotelsList;
	}

}
