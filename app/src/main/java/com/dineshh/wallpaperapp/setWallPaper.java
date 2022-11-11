package com.dineshh.wallpaperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class setWallPaper extends AppCompatActivity {
    Button setWallpaper_btn;
    Intent intent;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wall_paper);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
        setWallpaper_btn=findViewById(R.id.set_wallpaper_btn);
        imageView=findViewById(R.id.wallpaper);
        intent=getIntent();
        String url=intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(imageView);
        setWallpaper_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap=((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(getApplicationContext(),"SET wallpaper successfully",Toast.LENGTH_LONG).show();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });


    }
}