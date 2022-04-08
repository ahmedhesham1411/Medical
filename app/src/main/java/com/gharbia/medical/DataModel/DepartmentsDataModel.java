package com.gharbia.medical.DataModel;

public class DepartmentsDataModel {
    int id;
    String name;
    String imageUrl;
    int doctorsNumber;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDoctorsNumber() {
        return doctorsNumber;
    }

    public void setDoctorsNumber(int doctorsNumber) {
        this.doctorsNumber = doctorsNumber;
    }
}
