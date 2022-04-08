package com.gharbia.medical.DataModel;

public class ResetPasswordRequest {
    String NewPassword;

    public ResetPasswordRequest(String newPassword) {
        NewPassword = newPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }
}
