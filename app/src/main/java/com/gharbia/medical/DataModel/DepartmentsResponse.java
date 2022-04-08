package com.gharbia.medical.DataModel;

import java.util.List;

public class DepartmentsResponse {
    List<DepartmentsDataModel> data;

    public List<DepartmentsDataModel> getData() {
        return data;
    }

    public void setData(List<DepartmentsDataModel> data) {
        this.data = data;
    }
}
