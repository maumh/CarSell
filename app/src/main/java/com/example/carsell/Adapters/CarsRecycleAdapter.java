package com.example.carsell.Adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carsell.R;
import com.example.carsell.model.CarData;
import com.example.carsell.model.CarsList;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;

public class CarsRecycleAdapter extends RecyclerView.Adapter<CarsRecycleAdapter.MyViewHolder> {
    private CarsList carsList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView carImage;
        public TextView txtCarBrand, txtIsUsedCar, txtCarConstractionYear;

        public MyViewHolder(View view, ImageView carImage,TextView txtCarBrand, TextView txtIsUsedCar, TextView txtCarConstractionYear ) {
            super(view);
            this.carImage = carImage;
            this.txtCarBrand = txtCarBrand;
            this.txtIsUsedCar = txtIsUsedCar;
            this.txtCarConstractionYear = txtCarConstractionYear;
        }
    }


    public CarsRecycleAdapter(CarsList carsList) {
        this.carsList = carsList;
    }

    public void addData(CarsList carsList){
        if(this.carsList != null && this.carsList.carDataList != null && carsList.carDataList != null){
            this.carsList.carDataList.addAll(carsList.carDataList);
            notifyDataSetChanged();
        }
    }


    @Override
    public CarsRecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {

        View view = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_data_cell, parent, false);
        MyViewHolder vh = new MyViewHolder(view,(ImageView) view.findViewById(R.id.carImage),(TextView) view.findViewById(R.id.txtCarBrand),(TextView) view.findViewById(R.id.txtIsUsedCar), (TextView) view.findViewById(R.id.txtCarConstractionYear));
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CarData carData = carsList.carDataList.get(position);
        holder.txtCarBrand.setText(carData.carBrand);
        holder.txtCarConstractionYear.setText(carData.carConstructionYear);
        holder.txtIsUsedCar.setText(carData.isUsedCar ? "Used" : "New");

        Picasso.get().load(carData.carImageUrl).into(holder.carImage);
    }


    @Override
    public int getItemCount() {
        return carsList.carDataList.size();
    }
}
