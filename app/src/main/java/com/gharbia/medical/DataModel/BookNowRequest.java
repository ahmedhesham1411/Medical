package com.gharbia.medical.DataModel;

public class BookNowRequest {
    int HourId;
    int Age;
    String FullName;
    int Sex;
    String PhoneNumber;
    String Note;

    public BookNowRequest(int hourId, int age, String fullName, int sex, String phoneNumber, String note) {
        HourId = hourId;
        Age = age;
        FullName = fullName;
        Sex = sex;
        PhoneNumber = phoneNumber;
        Note = note;
    }

    public int getHourId() {
        return HourId;
    }

    public void setHourId(int hourId) {
        HourId = hourId;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
