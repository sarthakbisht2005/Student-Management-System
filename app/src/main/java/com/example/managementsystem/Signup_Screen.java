package com.example.managementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Signup_Screen extends AppCompatActivity {

    EditText Full_name;
    EditText Roll_no;
    EditText Branch;
    EditText Email;
    EditText Phone_no;
    EditText Registration_no;
    EditText Passwords;
    Button sumbitButton;
    String full_name, roll_no, branch, email, phone_no, registration_no, password;
    TextView textViewError;
    ProgressBar progressBar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        Full_name = findViewById(R.id.full_name);
        Roll_no = findViewById(R.id.roll_no);
        Branch = findViewById(R.id.branch);
        Email = findViewById(R.id.email_Id);
        Phone_no = findViewById(R.id.phone_no);
        Registration_no = findViewById(R.id.registration_no);
        Passwords = findViewById(R.id.passwords);
        textViewError = findViewById(R.id.error);
        progressBar = findViewById(R.id.loading);
        sumbitButton = findViewById(R.id.sumbitButton);

        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textViewError.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                full_name = String.valueOf(Full_name.getText());
                roll_no = String.valueOf(Roll_no.getText());
                branch = String.valueOf(Branch.getText());
                email = String.valueOf(Email.getText());
                phone_no = String.valueOf(Phone_no.getText());
                registration_no = String.valueOf(Registration_no.getText());
                password = String.valueOf(Passwords.getText());
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.28.38/loginSignup/signup.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                if (response.equals("success")) {
                                    Toast.makeText(getApplicationContext(), "Registrations successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login_Screen.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                                    textViewError.setText(response);
                                    textViewError.setVisibility(View.VISIBLE);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        textViewError.setText(error.getLocalizedMessage());
                        textViewError.setVisibility(View.VISIBLE);
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("fullName", full_name);
                        paramV.put("rollno", roll_no);
                        paramV.put("branch", branch);
                        paramV.put("email", email);
                        paramV.put("phoneNo", phone_no);
                        paramV.put("registrationNo", registration_no);
                        paramV.put("password", password);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });

    }

}