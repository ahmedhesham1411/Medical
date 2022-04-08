package com.gharbia.medical.DataModel;

import java.util.List;

public class ContactSliderResponse {

    List<ContactSliderResponse2> data;

    public List<ContactSliderResponse2> getData() {
        return data;
    }

    public void setData(List<ContactSliderResponse2> data) {
        this.data = data;
    }

    public class ContactSliderResponse2 {

        String imageUrl;
        String name;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
