package com.example.managementsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class Aboutus extends BaseActivity {

    TextView txtabout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_aboutus, drawerLayout, false);
        drawerLayout.addView(contentView, 0);

        txtabout = findViewById(R.id.txtabout);
        txtabout.setText("GOVERNMENT POLYTECHNIC GAUCHAR was establish in 1975. This is most efficient institute in all over Uttrakhand. G.P.Gauchar is associated to Uttarakhand board of technical education Roorkee. Every year an entrance exam is conducted by U.B.T.E. Roorkee. After qualifying this Exam(JEEP) students are selected through counseling for G.P.Gauchar. There are various branches are conducting in government polytechnic Gauchar. Some of the specific branches are also available here which are COMPUTER SCIENCE, & ENGINEERING and INFORMATION TECHNOLOGY.");

    }
}