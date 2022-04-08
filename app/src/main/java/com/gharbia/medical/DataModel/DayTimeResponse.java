package com.gharbia.medical.DataModel;

import java.util.List;

public class DayTimeResponse {

    List<DayTimeResponse2> data;

    public List<DayTimeResponse2> getDate() {
        return data;
    }

    public void setDate(List<DayTimeResponse2> date) {
        this.data = date;
    }

    public class DayTimeResponse2 {

        int id;
        String date;
        List<DayTimeResponse3> hours;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<DayTimeResponse3> getHours() {
            return hours;
        }

        public void setHours(List<DayTimeResponse3> hours) {
            this.hours = hours;
        }
    }


    public class DayTimeResponse3 {

        int id;
        String hour;
        boolean selected;
        boolean booked;

        public boolean isBooked() {
            return booked;
        }

        public void setBooked(boolean booked) {
            this.booked = booked;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }
    }
}
