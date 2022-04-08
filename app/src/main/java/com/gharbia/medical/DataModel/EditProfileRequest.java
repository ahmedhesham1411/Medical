package com.gharbia.medical.DataModel;

public class EditProfileRequest {
    String UserName;
    String Email;
    String Phone;
    String Image;

    public EditProfileRequest(String userName, String email, String phone, String image) {
        UserName = userName;
        Email = email;
        Phone = phone;
        Image = image;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
