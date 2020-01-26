package com.example.carsell.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarsList {

    @SerializedName("status")
    public int status;


    @SerializedName("data")
    public List<CarData> carDataList;
}
