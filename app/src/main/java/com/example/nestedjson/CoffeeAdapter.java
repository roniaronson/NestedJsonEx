package com.example.nestedjson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nestedjson.objects.Coffee;

import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeHolder> {

    private Context context;
    private List<Coffee> coffeeList;

    public CoffeeAdapter(Context context, List<Coffee> coffeeList) {
        this.context = context;
        this.coffeeList = coffeeList;
    }

    @NonNull
    @Override
    public CoffeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new CoffeeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeHolder holder, int position) {

        Coffee coffee = coffeeList.get(position);
        holder.region.setText(coffee.toStringRegions());
        Log.d("BDIKA",coffee.getRegions().toString() );

        holder.country.setText(coffee.getCountry().toString());
        //Glide.with(context).load(movie.getPoster()).into(holder.imageView);

       /* holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", movie.getTitle());
                bundle.putString("overView", movie.getOverView());
                bundle.putString("poster", movie.getPoster());
                bundle.putDouble("rating", movie.getRating());

                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    public class CoffeeHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView region, country;
        ConstraintLayout constraintLayout;

        public CoffeeHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            region = itemView.findViewById(R.id.region);
            country = itemView.findViewById(R.id.country);
            constraintLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}