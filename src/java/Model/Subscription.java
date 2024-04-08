/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Tom
 */
public class Subscription {
    private String location;
    private String retailerName;
    private String email;
    private String phoneNumber;

    private int RetailerId;

    
    /**
     * @return the retailerName
     */
    public String getRetailerName() {
        return retailerName;
    }

    /**
     * @param retailerName the retailerName to set
     */
    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the RetailerId
     */
    public int getRetailerId() {
        return RetailerId;
    }

    /**
     * @param RetailerId the RetailerId to set
     */
    public void setRetailerId(int RetailerId) {
        this.RetailerId = RetailerId;
    }
}
