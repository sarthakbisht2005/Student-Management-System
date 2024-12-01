package com.example.managementsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends BaseActivity {

    ViewFlipper flipper;
    ListView lv1;
    ListView lv2;
    ListView lv3;
    TextView txtnote;
    TextView txtnote2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_main, drawerLayout, false);
        drawerLayout.addView(contentView, 0);

        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));


        int[] imgarray = {R.drawable.poly, R.drawable.poly, R.drawable.poly};
        flipper = findViewById(R.id.slider);

        for (int i = 0; i < imgarray.length; i++) {
            showimage(imgarray[i]);
        }

        txtnote = findViewById(R.id.txtnote);
        txtnote.setText("Government Polytechnic Gauchar, established in 1979, is on of the most reputeted institute in Uttarakhand.G.P.Gauchar is affiliated to Uttarakhand Board of Technical Education Roorkee (UBTER) and approved by All India Council of Technical Education (AICTE). The diploma in pharmacy course is also approved by Pharmacy Council of India (PCI).\n"
                + "Students are admitted through the Joint Entrance Exam for Polytechnics (JEEP) which is conducted by UBTER, every year. The various courses are running in this institution, where in diploma in engineering, Civil Engg, Electronics Engg and Information Technology branches are currently running. There are 40 seats available in each branch. Diploma in pharmacy is also running in the institute with 40 seats, which is approved by PCI.");

        txtnote2 = findViewById(R.id.txtnote2);
        txtnote2.setText("As a principal of Government Polytechnic Gauchar, I am very pleased to welcome you to our website. The motto of G.P.Gauchar is to  impart value based quality education to students on multi-disciplines that enable them to nourish their career and contribute effectively in industrial & social development followed by economic growth of  the nation.\n" +
                "\t\t\t\tThe focus of education at G.P.Gauchar is to inculcate a sense of positive thinking and scientific outlook through practical orientation of moral percepts and cultural heritage. The institute provides sound academic environment to the students with world class faculty and the state of art technical infrastructure and laboratories for their all round development.\n" +
                "\t\t\t\t\tG.P.Gauchar is the institute where you can realize your true potential and achieve what you wish to be. I wish all the best to all the students for there future endeavors.");

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



