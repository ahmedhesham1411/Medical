package com.gharbia.medical.DataModel;

import java.util.List;

public class ClinicsDataModel {
    int clinicId;
    String name;
    String imageUrl;
    String description;
    int price;
    int reviwes;
    double rate;
    boolean sponserd;
    List<AddressModel> addresses;

    public int getReviwes() {
        return reviwes;
    }

    public void setReviwes(int reviwes) {
        this.reviwes = reviwes;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getClinicId() {
        return clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSponserd() {
        return sponserd;
    }

    public void setSponserd(boolean sponserd) {
        this.sponserd = sponserd;
    }

    public List<AddressModel> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressModel> addresses) {
        this.addresses = addresses;
    }
}
