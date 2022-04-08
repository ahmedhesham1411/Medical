package com.gharbia.medical.DataModel;

import java.util.List;

public class BookDetailsResponse {

    BookDetailsResponse2 data;

    public BookDetailsResponse2 getData() {
        return data;
    }

    public void setData(BookDetailsResponse2 data) {
        this.data = data;
    }

    public class BookDetailsResponse2 {
        String drName;
        double rate;
        int bookingStatus;
        int reviewsCount;
        String drUrl;
        String day;
        String hour;
        String masterDegree;
        List<String> slider;
        int medicalId;
        String cost;
        boolean isreview;
        String statues;
        String addressName;
        String address;
        int bookId;
        String description;
        String patientName;
        int age;
        String gender;
        String patientNote;
        String whatsAppNumber;
        String drPhone;
        double latitude;
        double longitude;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getBookingStatus() {
            return bookingStatus;
        }

        public void setBookingStatus(int bookingStatus) {
            this.bookingStatus = bookingStatus;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public int getStatus() {
            return bookingStatus;
        }

        public void setStatus(int status) {
            this.bookingStatus = status;
        }

        public String getDrName() {
            return drName;
        }

        public void setDrName(String drName) {
            this.drName = drName;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public int getReviewsCount() {
            return reviewsCount;
        }

        public void setReviewsCount(int reviewsCount) {
            this.reviewsCount = reviewsCount;
        }

        public String getDrUrl() {
            return drUrl;
        }

        public void setDrUrl(String drUrl) {
            this.drUrl = drUrl;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public String getMasterDegree() {
            return masterDegree;
        }

        public void setMasterDegree(String masterDegree) {
            this.masterDegree = masterDegree;
        }

        public List<String> getSlider() {
            return slider;
        }

        public void setSlider(List<String> slider) {
            this.slider = slider;
        }

        public int getMedicalId() {
            return medicalId;
        }

        public void setMedicalId(int medicalId) {
            this.medicalId = medicalId;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public boolean isIsreview() {
            return isreview;
        }

        public void setIsreview(boolean isreview) {
            this.isreview = isreview;
        }

        public String getStatues() {
            return statues;
        }

        public void setStatues(String statues) {
            this.statues = statues;
        }

        public String getAddressName() {
            return addressName;
        }

        public void setAddressName(String addressName) {
            this.addressName = addressName;
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPatientNote() {
            return patientNote;
        }

        public void setPatientNote(String patientNote) {
            this.patientNote = patientNote;
        }

        public String getWhatsAppNumber() {
            return whatsAppNumber;
        }

        public void setWhatsAppNumber(String whatsAppNumber) {
            this.whatsAppNumber = whatsAppNumber;
        }

        public String getDrPhone() {
            return drPhone;
        }

        public void setDrPhone(String drPhone) {
            this.drPhone = drPhone;
        }
    }
}
