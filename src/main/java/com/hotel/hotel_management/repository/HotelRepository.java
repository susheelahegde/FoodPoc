package com.hotel.hotel_management.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotel.hotel_management.entity.HotelEntity;

@Repository
public interface HotelRepository extends CrudRepository<HotelEntity, String> {
	@Query(value = "UPDATE hotel SET hotel_name =?0, hotel_location =?1, contact_number=?2 WHERE id=?3;" )
	HotelEntity update(String name, String location, String contact , String id);
	
	@Query(value="Select * from hotel where hotel_name=?0 allow filtering")
	List<HotelEntity> findHotelByHotelName(String name);
	
	@Query(value="Select * from hotel where hotel_location=?0 allow filtering")
	List<HotelEntity> findHotelByLocation(String location);
	
	@Query(value="Select * from hotel where hotel_location=?0 and hotel_name=?1 allow filtering")
	List<HotelEntity> findHotelByfilters(String location, String name);

}