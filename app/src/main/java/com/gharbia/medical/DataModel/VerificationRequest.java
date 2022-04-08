package com.gharbia.medical.DataModel;

public class VerificationRequest {
    String Phone;
    int VerificationCode;

    public VerificationRequest(String phone, int verificationCode) {
        Phone = phone;
        VerificationCode = verificationCode;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getVerificationCode() {
        return VerificationCode;
    }

    public void setVerificationCode(int verificationCode) {
        VerificationCode = verificationCode;
    }
}
