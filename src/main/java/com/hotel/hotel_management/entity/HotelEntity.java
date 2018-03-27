package com.hotel.hotel_management.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

@Table("hotel")
public class HotelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @PrimaryKey
    private String id;
    @NotNull
    private String hotel_name;
    @NotNull
    private String hotel_location;
    @NotNull
    private String contact_number;

    public HotelEntity() {

    }

    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_location() {
        return hotel_location;
    }

    public void setHotel_location(String hotel_location) {
        this.hotel_location = hotel_location;
    }

	/**
	 * @return the contact_number
	 */
	public String getContact_number() {
		return contact_number;
	}

	/**
	 * @param contact_number the contact_number to set
	 */
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HotelEntity [id=" + id + ", hotel_name=" + hotel_name + ", hotel_location=" + hotel_location
				+ ", contact_number=" + contact_number + "]";
	}

	
	

}
