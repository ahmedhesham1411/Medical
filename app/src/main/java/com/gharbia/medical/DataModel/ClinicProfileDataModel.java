package com.gharbia.medical.DataModel;

import java.util.List;

public class ClinicProfileDataModel {
    String name;
    String drName;
    String imageUrl;
    List<AddressModel> addresses;
    String description;
    int price;
    int rate;
    boolean sponserd;
    String drPhone;
    String medicalProfilePhone;
    String whatsappPhone;
    String facebookLink;
    String instgramLink;
    String youTubeLink;
    String webSiteLink;
    String departmentName;
    List<String> medicalImages;
    List<DepartmentsDataModel> medicalDepartments;

    public List<DepartmentsDataModel> getMedicalDepartments() {
        return medicalDepartments;
    }

    public void setMedicalDepartments(List<DepartmentsDataModel> medicalDepartments) {
        this.medicalDepartments = medicalDepartments;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<AddressModel> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressModel> addresses) {
        this.addresses = addresses;
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

    public String getDrPhone() {
        return drPhone;
    }

    public void setDrPhone(String drPhone) {
        this.drPhone = drPhone;
    }

    public String getMedicalProfilePhone() {
        return medicalProfilePhone;
    }

    public void setMedicalProfilePhone(String medicalProfilePhone) {
        this.medicalProfilePhone = medicalProfilePhone;
    }

    public String getWhatsappPhone() {
        return whatsappPhone;
    }

    public void setWhatsappPhone(String whatsappPhone) {
        this.whatsappPhone = whatsappPhone;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getInstgramLink() {
        return instgramLink;
    }

    public void setInstgramLink(String instgramLink) {
        this.instgramLink = instgramLink;
    }

    public String getYouTubeLink() {
        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
    }

    public String getWebSiteLink() {
        return webSiteLink;
    }

    public void setWebSiteLink(String webSiteLink) {
        this.webSiteLink = webSiteLink;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<String> getMedicalImages() {
        return medicalImages;
    }

    public void setMedicalImages(List<String> medicalImages) {
        this.medicalImages = medicalImages;
    }
}
