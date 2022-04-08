package com.gharbia.medical.DataModel;

import java.util.List;

public class NotiResponse {

    List<NotiResponse2> data;

    public List<NotiResponse2> getData() {
        return data;
    }

    public void setData(List<NotiResponse2> data) {
        this.data = data;
    }

    public class NotiResponse2 {

        int notificationId;
        String title;
        String description;
        boolean isSeen;
        int requestId;
        int type;
        String time;

        public int getNotificationId() {
            return notificationId;
        }

        public void setNotificationId(int notificationId) {
            this.notificationId = notificationId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isSeen() {
            return isSeen;
        }

        public void setSeen(boolean seen) {
            isSeen = seen;
        }

        public int getRequestId() {
            return requestId;
        }

        public void setRequestId(int requestId) {
            this.requestId = requestId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
