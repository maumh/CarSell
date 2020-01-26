package com.example.carsell.retrofitUtils;

import com.example.carsell.model.CarsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/api/v1/cars?")
    Call<CarsList> getCarsList(@Query("page") String page);

}
