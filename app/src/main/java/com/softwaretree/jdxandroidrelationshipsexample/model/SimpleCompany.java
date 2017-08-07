package com.softwaretree.jdxandroidrelationshipsexample.model;

import java.util.List;

/**
 * A class describing a Company. It has 0 or more departments and 0 or more foreign locations.
 * <p>
 * @author Damodar
 *
 */
public class SimpleCompany {

    private String companyId;
    private String companyName;
    private String city;
    private String state;
    private String country;
    private SimpleForeignLocation[] foreignLocations;
    private List<SimpleDept> depts; // identified by companyId
    
    
    public SimpleCompany() {
        super();
    }

    public SimpleCompany(String companyId, String companyName, String city, String state, String country) {
        super();
        this.companyId = companyId;
        this.companyName = companyName;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
	public SimpleForeignLocation[] getForeignLocations() {
		return foreignLocations;
	}

	public void setForeignLocations(SimpleForeignLocation[] foreignLocations) {
		this.foreignLocations = foreignLocations;
	}

    public List<SimpleDept> getDepts() {
        return depts;
    }

    public void setDepts(List<SimpleDept> depts) {
        this.depts = depts;
    }

}
