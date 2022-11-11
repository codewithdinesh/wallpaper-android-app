package com.dineshh.wallpaperapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;


    ArrayList<ImageModel> wallpaperList;

    public Adapter(Context context, ArrayList<ImageModel> wallpaperList) {
        this.context = context;
        this.wallpaperList = wallpaperList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textView;
        public ViewHolder(View view) {
            super(view);
            imageView=view.findViewById(R.id.image);
            textView=view.findViewById(R.id.text);

        }
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        View view=LayoutInflater.from(context).inflate(R.layout.item_layout,null,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(Adapter.ViewHolder viewHolder, int position) {
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.textView.setMovementMethod(LinkMovementMethod.getInstance());
                viewHolder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browerintent =new Intent(Intent.ACTION_VIEW);

                        Intent intent = browerintent.setData(Uri.parse("https://www.pexels.com/"));
                        browerintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(browerintent);

                    }
                });
            }
        });
        Glide.with(context).load(wallpaperList.get(position).getSrc().getPortrait()).into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,setWallPaper.class);
                intent.putExtra("image",wallpaperList.get(position).getSrc().getPortrait());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {

        return wallpaperList.size();
    }
}
