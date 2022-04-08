package com.gharbia.medical.DataModel;

public class LoginResponse {
    UserModel data;

    public LoginResponse(UserModel data) {
        this.data = data;
    }

    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }
}
