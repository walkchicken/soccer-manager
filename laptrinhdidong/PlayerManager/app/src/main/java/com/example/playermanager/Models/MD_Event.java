package com.example.playermanager.Models;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Fazal Ur Rehman on 6/4/2018.
 */

public class MD_Event implements Serializable {
    public int id;
    public int hallId;
    public String name;
    public String date;
    public int timeInMins;
    public int totalCapacity;
    public String hallName;
    public int hallPosition;

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getHallPosition() {
        return hallPosition;
    }

    public void setHallPosition(int hallPosition) {
        this.hallPosition = hallPosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTimeInMins() {
        return timeInMins;
    }

    public void setTimeInMins(int timeInMins) {
        this.timeInMins = timeInMins;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }
}
