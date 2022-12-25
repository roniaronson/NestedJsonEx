package com.example.nestedjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nestedjson.objects.Coffee;
import com.example.nestedjson.objects.Country;
import com.example.nestedjson.objects.Region;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Coffee> coffeeList;
    private Region[]regions;
    private Country country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingelton.getmIstance(this).getRequestQueue();

        coffeeList = new ArrayList<>();

        fetchMovies();
    }

    private void fetchMovies() {

        String url = "https://gist.githubusercontent.com/robertcedwards/8574169/raw/90c2d25d3ad5fd0b5f3a56b523e409ce7e0e9480/sample_nested.json";

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject2 = response.getJSONObject("coffee");
                    try {
                        JSONArray jsonArray = jsonObject2.getJSONArray("region");
                        regions = new Region[jsonArray.length()];
                        for (int i = 0; i < 2; i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            String name = jsonObject.getString("name");
                            Log.d("PRINT", name);
                            Region region = new Region(id, name);
                            regions[i] = region;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject jsonObject = jsonObject2.getJSONObject("country");
                        int id = jsonObject.getInt("id");
                        String company = jsonObject.getString("company");

                        Log.d("PRINT", company);
                        country = new Country(id, company);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Coffee coffee= new Coffee(regions, country);
                coffeeList.add(coffee);
                Log.d("PRINT", "allGood");
                CoffeeAdapter adapter = new CoffeeAdapter(MainActivity.this, coffeeList);
                recyclerView.setAdapter(adapter);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}