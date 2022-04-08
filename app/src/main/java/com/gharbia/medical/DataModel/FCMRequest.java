package com.gharbia.medical.DataModel;

public class FCMRequest {
    String PushToken;
    int OS;

    public FCMRequest(String pushToken, int OS) {
        PushToken = pushToken;
        this.OS = OS;
    }

    public String getPushToken() {
        return PushToken;
    }

    public void setPushToken(String pushToken) {
        PushToken = pushToken;
    }

    public int getOS() {
        return OS;
    }

    public void setOS(int OS) {
        this.OS = OS;
    }
}
