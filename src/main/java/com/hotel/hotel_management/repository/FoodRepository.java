/**
 * 
 */
package com.hotel.hotel_management.repository;


import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hotel.hotel_management.entity.FoodEntity;

public interface FoodRepository extends CrudRepository<FoodEntity, String> {
	
	@Query(value = "Delete FROM food WHERE hotel_id=?0 allow filtering")
	void deleteFoodByHotelId(String id);
	
	@Query(value = "Select * FROM food WHERE hotel_id=?0 allow filtering")
	List<FoodEntity> findFoodByHotel(String id);
	
	@Query(value = "Select * FROM food WHERE food_id=?0 and hotel_id=?1 allow filtering")
	FoodEntity findFoodByFoodId(String food_id, String hotel_id);
	
	@Query(value = "UPDATE food SET food_name =?0, food_price =?1, hotel_id=?2 where food_id=?3" )
	FoodEntity update(String name, String price, String hotel_id , String food_id);

	@Query(value = "Select * from food where hotel_id=?0 allow filtering")
	List<FoodEntity> findHotelByfilters(String id);

	@Query(value = "Select * from food where hotel_id=?0 allow filtering")
	FoodEntity findHotelById(String id);
}
