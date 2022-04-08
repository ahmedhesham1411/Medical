package com.gharbia.medical.DataModel;

import java.util.List;

public class ReviewResponse {

    List<ReviewResponse2> data;

    public List<ReviewResponse2> getData() {
        return data;
    }

    public void setData(List<ReviewResponse2> data) {
        this.data = data;
    }

    public class ReviewResponse2 {

        String userId;
        String content;
        int rate;
        String fullName;
        String email;
        int medicalId;
        int reviewCount;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getMedicalId() {
            return medicalId;
        }

        public void setMedicalId(int medicalId) {
            this.medicalId = medicalId;
        }

        public int getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(int reviewCount) {
            this.reviewCount = reviewCount;
        }
    }
}
