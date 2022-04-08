package com.gharbia.medical.DataModel;

public class RegisterRequest {
    String Name;
    String Password;
    String PhoneNumber;
    String Email;
    int CityId;
    String Image;

    public RegisterRequest(String name, String password, String phoneNumber, String email, int cityId, String image) {
        Name = name;
        Password = password;
        PhoneNumber = phoneNumber;
        Email = email;
        CityId = cityId;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
