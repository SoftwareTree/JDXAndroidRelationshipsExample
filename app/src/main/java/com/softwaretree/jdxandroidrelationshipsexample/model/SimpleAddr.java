package com.softwaretree.jdxandroidrelationshipsexample.model;

/**
 * A class describing an Address.
 * <p>
 * Special note: the addrId attribute is declared as an IMPLICIT_ATTRIB in the mapping specification 
 * for the class SimpleAddr; and so, it will automatically be initialized with the related value in 
 * the referencing (SimpleEmp) object when that instance of SimpleEmp is persisted by JDX ORM. 
 * If addrId had not been declared as an IMPLICIT_ATTRIB in the mapping specification, it should 
 * be explicitly initialized outside of JDX before the instance of a SimpleAddr is persisted.
 * <p>
 * @author Damodar
 *
 */
public class SimpleAddr {
    private String addrId;
    private String addr1;
    private String addr2;
    private String city;
    private String state;
    private String zip;
    private String country;
    
    public SimpleAddr() {
        super();
    }

    public SimpleAddr(String addr1, String addr2, String city, String state, String zip, String country) {
        super();
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public String getAddrId() {
        return addrId;
    }

    public void setAddrId(String addrId) {
        this.addrId = addrId;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }    
    
}
