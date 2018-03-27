package com.hotel.hotel_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.hotel_management.entity.HotelEntity;

@Service
public interface HotelService {
    public HotelEntity save(HotelEntity hotelEntity);
    
    public HotelEntity updateHotel(HotelEntity hotelEntity);
    
    public void deleteHotel(String id);

	public List<HotelEntity> getHotelsByFilters(String hotelName, String location);

}
