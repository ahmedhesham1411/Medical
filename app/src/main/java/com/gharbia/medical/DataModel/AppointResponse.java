package com.gharbia.medical.DataModel;

public class AppointResponse {
    String day;
    String hour;
    String drName;
    String branchName;
    String whatsAppNumber;
    String description;
    String cost;
    String note;
    String drImage;
    int rate;
    int reviwes;
    int bookId;
    int medicalId;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(int medicalId) {
        this.medicalId = medicalId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getReviwes() {
        return reviwes;
    }

    public void setReviwes(int reviwes) {
        this.reviwes = reviwes;
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

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getWhatsAppNumber() {
        return whatsAppNumber;
    }

    public void setWhatsAppNumber(String whatsAppNumber) {
        this.whatsAppNumber = whatsAppNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDrImage() {
        return drImage;
    }

    public void setDrImage(String drImage) {
        this.drImage = drImage;
    }
}
