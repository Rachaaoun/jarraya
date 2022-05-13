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
public class Stay {
      private int id ;
     private int usersId;
      private int stayId;
    private String capacity;
    private String description;
    private String photo;
    private String startdateav;
    private String enddateav;
     private String nbBooked;

    public Stay() {
    }

    public Stay(int usersId, int stayId, String capacity, String description, String photo, String startdateav, String enddateav, String nbBooked) {
        this.usersId = usersId;
        this.stayId = stayId;
        this.capacity = capacity;
        this.description = description;
        this.photo = photo;
        this.startdateav = startdateav;
        this.enddateav = enddateav;
        this.nbBooked = nbBooked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public int getStayId() {
        return stayId;
    }

    public void setStayId(int stayId) {
        this.stayId = stayId;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStartdateav() {
        return startdateav;
    }

    public void setStartdateav(String startdateav) {
        this.startdateav = startdateav;
    }

    public String getEnddateav() {
        return enddateav;
    }

    public void setEnddateav(String enddateav) {
        this.enddateav = enddateav;
    }

    public String getNbBooked() {
        return nbBooked;
    }

    public void setNbBooked(String nbBooked) {
        this.nbBooked = nbBooked;
    }
     
     
    
}
