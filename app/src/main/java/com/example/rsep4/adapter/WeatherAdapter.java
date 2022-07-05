package com.example.rsep4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.rsep4.R;
import com.example.rsep4.models.WeatherModel;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private Context context;
    private List<WeatherModel> weatherList;
    public WeatherAdapter(Context context, List<WeatherModel> weatherList){
        this.context = context;
        this.weatherList = weatherList;
    }
    public void setWeatherList(List<WeatherModel> weatherList) {
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new WeatherViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        holder.textView.setText(this.weatherList.get(position).getCity().toString());
        Glide.with(context).load(this.weatherList.get(position).getIcon()).apply(RequestOptions.centerCropTransform()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(weatherList!=null)
        return weatherList.size();
        return 0;
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.weatherTextView);
            imageView=itemView.findViewById(R.id.weatherImageView);
        }
    }
}


