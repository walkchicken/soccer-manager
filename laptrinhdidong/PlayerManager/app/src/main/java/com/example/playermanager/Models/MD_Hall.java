package com.example.playermanager.Models;

import android.location.Location;
import android.media.Rating;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fazal Ur Rehman on 6/4/2018.
 */

public class MD_Hall implements Serializable {
    public int id;
    public String name;
    public String address;
    public int totalCapacity;
    public int reviewCount;
    public float rating;
    public String about;
    public String userType;

    public int getHallPosition() {
        return hallPosition;
    }

    public void setHallPosition(int hallPosition) {
        this.hallPosition = hallPosition;
    }

    public int hallPosition;

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public int currentCapacity;

    public List<MD_Event> getEventListInside() {
        return eventListInside;
    }

    public void setEventListInside(List<MD_Event> eventListInside) {
        this.eventListInside = eventListInside;
    }

    public List<MD_Event> eventListInside;


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
