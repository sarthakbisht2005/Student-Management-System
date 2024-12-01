package com.example.managementsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Gallery extends BaseActivity {
    ViewFlipper flipper;
    private WebView mywebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_gallery, drawerLayout, false);
        drawerLayout.addView(contentView, 0);

        int[] imgarray = {R.drawable.poly, R.drawable.poly, R.drawable.poly};
        flipper = findViewById(R.id.sliders);

        for (int i = 0; i < imgarray.length; i++) {
            showimage(imgarray[i]);
        }

    }

    public void showimage(int img) {
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(img);
        flipper.addView(iv);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);
        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }


}