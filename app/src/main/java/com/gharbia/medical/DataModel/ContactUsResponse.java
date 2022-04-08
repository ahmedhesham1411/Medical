package com.gharbia.medical.DataModel;

public class ContactUsResponse {

    ContactUsResponse2 data;

    public ContactUsResponse2 getData() {
        return data;
    }

    public void setData(ContactUsResponse2 data) {
        this.data = data;
    }

    public class ContactUsResponse2 {
        String facebook;
        String instgram;
        String linkedIn;
        String address;
        String phoneNumber;
        String description;
        String email;
        String youtube;
        String websiteLink;
        double latitude;
        double longitude;

        public String getYoutube() {
            return youtube;
        }

        public void setYoutube(String youtube) {
            this.youtube = youtube;
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

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public String getInstgram() {
            return instgram;
        }

        public void setInstgram(String instgram) {
            this.instgram = instgram;
        }

        public String getLinkedIn() {
            return linkedIn;
        }

        public void setLinkedIn(String linkedIn) {
            this.linkedIn = linkedIn;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWebsiteLink() {
            return websiteLink;
        }

        public void setWebsiteLink(String websiteLink) {
            this.websiteLink = websiteLink;
        }
    }

}
