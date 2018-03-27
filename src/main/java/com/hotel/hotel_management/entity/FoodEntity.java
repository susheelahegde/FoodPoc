package com.hotel.hotel_management.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Table("food")
public class FoodEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @PrimaryKey
    private String food_id;
    @NotNull
    private String food_name;
    @NotNull
    private String food_price;
    @NotNull
    private String hotel_id;

    public FoodEntity() {

    }
    
	public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }

	/**
	 * @return the food_id
	 */
	public String getFood_id() {
		return food_id;
	}

	/**
	 * @param food_id the food_id to set
	 */
	public void setFood_id(String food_id) {
		this.food_id = food_id;
	}

	/**
	 * @return the hotel_id
	 */
	public String getHotel_id() {
		return hotel_id;
	}

	/**
	 * @param hotel_id the hotel_id to set
	 */
	public void setHotel_id(String hotel_id) {
		this.hotel_id = hotel_id;
	}

	@Override
	public String toString() {
		return "FoodEntity [food_id=" + food_id + ", food_name=" + food_name + ", food_price=" + food_price
				+ ", hotel_id=" + hotel_id + "]";
	}
	

}
