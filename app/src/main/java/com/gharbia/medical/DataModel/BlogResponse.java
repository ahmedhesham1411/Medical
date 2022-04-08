package com.gharbia.medical.DataModel;

import java.util.List;

public class BlogResponse {

    List<BlogResponse2> data;

    public List<BlogResponse2> getData() {
        return data;
    }

    public void setData(List<BlogResponse2> data) {
        this.data = data;
    }

    public class BlogResponse2 {
        int id;
        String title;
        String imageUrl;
        String videoUrl;
        String description;
        String craetedOn;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCraetedOn() {
            return craetedOn;
        }

        public void setCraetedOn(String craetedOn) {
            this.craetedOn = craetedOn;
        }
    }
}
