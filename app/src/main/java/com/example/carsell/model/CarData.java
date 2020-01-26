package com.example.carsell.model;

import com.google.gson.annotations.SerializedName;

public class CarData {

    @SerializedName("id")
    public int carID;

    @SerializedName("brand")
    public String carBrand;

    @SerializedName("constructionYear")
    public String carConstructionYear;

    @SerializedName("isUsed")
    public boolean isUsedCar;

    @SerializedName("imageUrl")
    public String carImageUrl;
}
