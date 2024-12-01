package com.example.managementsystem;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Profile_Screen extends AppCompatActivity {
    TextView textViewName, textViewEmail, textViewRollno, textViewRegsno, textViewPhoneno, textViewBranch, textViewFetchResult;
    SharedPreferences sharedPreferences;
    Button buttonFetchUser;
    private WebView mywebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        textViewName = findViewById(R.id.name);
        textViewEmail = findViewById(R.id.email);
        textViewRollno = findViewById(R.id.rollno);
        textViewRegsno = findViewById(R.id.regs);
        textViewPhoneno = findViewById(R.id.phone);
        textViewBranch = findViewById(R.id.branch);
        textViewFetchResult = findViewById(R.id.fetchResult);
        buttonFetchUser = findViewById(R.id.fetchProfile);
        sharedPreferences = getSharedPreferences("MyappName", MODE_PRIVATE);

        //  if (sharedPreferences.getString("logged", "false").equals("false")) {
        // Intent intent = new Intent(getApplicationContext(), Login_Screen.class);
        //  startActivity(intent);
        //    finish();
        //  }

        textViewRegsno.setText(sharedPreferences.getString("registrationNo", ""));
        textViewName.setText(sharedPreferences.getString("fullName", ""));
        textViewEmail.setText(sharedPreferences.getString("email", ""));
        textViewBranch.setText(sharedPreferences.getString("branch", ""));
        textViewRollno.setText(sharedPreferences.getString("rollno", ""));
        textViewPhoneno.setText(sharedPreferences.getString("phoneNo", ""));

        buttonFetchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.28.38/loginSignup/profile.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                textViewFetchResult.setText(response);
                                textViewFetchResult.setVisibility(View.VISIBLE);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("email", sharedPreferences.getString("email", ""));
                        paramV.put("apiKey", sharedPreferences.getString("apiKey", ""));
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
}