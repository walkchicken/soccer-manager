package com.example.loginregister.activity.loginregister.Clubs;
import com.google.gson.annotations.SerializedName;

public class Clubs {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("weight")
    private String weight;
    @SerializedName("height")
    private String height;
    @SerializedName("position")
    private int position;
    @SerializedName("birth")
    private String birth;
    @SerializedName("picture")
    private String picture;
    @SerializedName("love")
    private Boolean love;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getLove() {
        return love;
    }

    public void setLove(Boolean love) {
        this.love = love;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
