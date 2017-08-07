package com.softwaretree.jdxandroidrelationshipsexample.model;

public class SimpleForeignLocation {
	private int locationId; // auto-generated
	private String companyId;
    private String city;
    private String country;
    
	public SimpleForeignLocation() {
		super();
	}
	
	public SimpleForeignLocation(String companyId, String city, String country) {
		super();
		this.companyId = companyId;
		this.city = city;
		this.country = country;
	}
	
	public int getLocationId() {
		return locationId;
	}
	
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public String getCompanyId() {
		return companyId;
	}
	
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
 
}
