package com.dineshh.wallpaperapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;

    Adapter adapter;
    CardView nnature,ncity,ndoremon,ntrain,ntrending,ncar;
    EditText searchText;
    ImageButton search,more;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        searchText=findViewById(R.id.search_text);
        search=findViewById(R.id.serach);
        more=findViewById(R.id.more);

        nnature=findViewById(R.id.nature);
        ncity=findViewById(R.id.city);
        ntrain=findViewById(R.id.train);
        ntrending=findViewById(R.id.trending);
        ncar=findViewById(R.id.car);
        ndoremon=findViewById(R.id.doremon);
        modelClassList=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(getApplicationContext(),modelClassList);
        recyclerView.setAdapter(adapter);
        findPhotos();
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Created By Dinesh Rathod , Thanks Pixels.com For Giving such amazing API", Toast.LENGTH_LONG).show();
            }
        });
        nnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="nature";
                getSearchImage(query);

            }
        });

        ncity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="city";
                getSearchImage(query);

            }
        });
        ntrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="train";
                getSearchImage(query);

            }
        });
        ncar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="car";
                getSearchImage(query);

            }
        });
        ntrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         findPhotos();

            }
        });
        ndoremon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="cartoon";
                getSearchImage(query);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Search_query=searchText.getText().toString().toLowerCase();
                if (Search_query.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter Something to search", Toast.LENGTH_SHORT).show();

                }else {
                    getSearchImage(Search_query);
                }
            }
        });

        

    }

    private void getSearchImage(String query) {
        ApiUtility.getApiInterface().getImage(query,1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if (response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "Something Went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }

    private void findPhotos() {
        ApiUtility.getApiInterface().getImage(1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if (response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }     else {
                    Toast.makeText(MainActivity.this, "Something Went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }
}