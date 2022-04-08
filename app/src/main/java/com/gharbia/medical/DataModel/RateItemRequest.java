package com.gharbia.medical.DataModel;

public class RateItemRequest {
    int MedicalId;
    String Content;
    int Rate;

    public RateItemRequest(int medicalId, String content, int rate) {
        MedicalId = medicalId;
        Content = content;
        Rate = rate;
    }

    public int getMedicalId() {
        return MedicalId;
    }

    public void setMedicalId(int medicalId) {
        MedicalId = medicalId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }
}
