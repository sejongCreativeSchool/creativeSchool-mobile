package com.booreum.model;

import java.util.List;

public class ErrandResults {
    int status;
    List<Errand> data;

    public ErrandResults() {
    }

    public ErrandResults(int status, List<Errand> data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Errand> getData() {
        return data;
    }

    public void setData(List<Errand> data) {
        this.data = data;
    }
}
