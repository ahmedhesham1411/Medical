package com.gharbia.medical.DataModel;

import java.util.List;

public class HospitalsResponse {

    List<HospitalsData> data;

    public List<HospitalsData> getData() {
        return data;
    }

    public void setData(List<HospitalsData> data) {
        this.data = data;
    }

    public static class HospitalsData {
        int id;
        String name;
        String drName;
        String drPhone;
        String imageUrl;
        String medicalPhoneNumber;
        List<AddressModel> addresses;
        String description;
        boolean sponserd;

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

        public String getDrName() {
            return drName;
        }

        public void setDrName(String drName) {
            this.drName = drName;
        }

        public String getDrPhone() {
            return drPhone;
        }

        public void setDrPhone(String drPhone) {
            this.drPhone = drPhone;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getMedicalPhoneNumber() {
            return medicalPhoneNumber;
        }

        public void setMedicalPhoneNumber(String medicalPhoneNumber) {
            this.medicalPhoneNumber = medicalPhoneNumber;
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

        public boolean isSponserd() {
            return sponserd;
        }

        public void setSponserd(boolean sponserd) {
            this.sponserd = sponserd;
        }
    }

}
