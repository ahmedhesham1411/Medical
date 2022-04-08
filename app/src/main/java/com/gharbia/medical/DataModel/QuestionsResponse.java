package com.gharbia.medical.DataModel;

import java.util.List;

public class QuestionsResponse {
    List<QuestionsDataModel> data;

    public List<QuestionsDataModel> getData() {
        return data;
    }

    public void setData(List<QuestionsDataModel> data) {
        this.data = data;
    }
}
