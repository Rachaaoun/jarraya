/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author raoun
 */
public class Booking {
    private int id;
     private String bookingDate;
     private String firstDate;
     private String endDate;
      private int userId;
      private int stayId;

    public Booking() {
    }

    public Booking(String bookingDate, String firstDate, String endDate, int userId, int stayId) {
        this.bookingDate = bookingDate;
        this.firstDate = firstDate;
        this.endDate = endDate;
        this.userId = userId;
        this.stayId = stayId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStayId() {
        return stayId;
    }

    public void setStayId(int stayId) {
        this.stayId = stayId;
    }
      
      
}
