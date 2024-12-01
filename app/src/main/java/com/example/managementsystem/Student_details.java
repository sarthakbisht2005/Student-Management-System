package com.example.managementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Student_details extends Signup_Screen {

    TextView textViewFetchResult;
    Button readbtn, createbtn, buttonUpdate, deletebtn;
    FloatingActionButton refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        Full_name = findViewById(R.id.name);
        Roll_no = findViewById(R.id.rollno);
        Email = findViewById(R.id.email);
        Branch = findViewById(R.id.branch);
        Phone_no = findViewById(R.id.phone);
        Registration_no = findViewById(R.id.regs);
        Passwords = findViewById(R.id.pass);

        textViewFetchResult = findViewById(R.id.fetchResult);
        readbtn = findViewById(R.id.fetchProfile);
        createbtn = findViewById(R.id.createProfile);
        buttonUpdate = findViewById(R.id.updateProfile);
        deletebtn = findViewById(R.id.delete);


        refresh = findViewById(R.id.refersh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Screen Refreshed !", Toast.LENGTH_SHORT).show();
            }
        });

        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.28.38/loginSignup/read.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        registration_no = jsonObject.getString("registrationNo");
                                        full_name = jsonObject.getString("fullName");
                                        email = jsonObject.getString("email");
                                        branch = jsonObject.getString("branch");
                                        phone_no = jsonObject.getString("phoneNo");
                                        roll_no = jsonObject.getString("rollno");
                                        String apiKey = jsonObject.getString("apikey");
                                        textViewFetchResult.append("Registration No : " + registration_no + "\nFull Name : " + full_name
                                                + "\nEmail : " + email + "\nBranch : " + branch + "\nPhone No : "
                                                + phone_no + "\nRoll No : " + roll_no + "\nApi Key : " + apiKey + "\n\n");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.getLocalizedMessage());
                    }
                });
                queue.add(stringRequest);
            }
        });

        createbtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        }));

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                full_name = String.valueOf(Full_name.getText());
                roll_no = String.valueOf(Roll_no.getText());
                email = String.valueOf(Email.getText());
                branch = String.valueOf(Branch.getText());
                phone_no = String.valueOf(Phone_no.getText());
                registration_no = String.valueOf(Registration_no.getText());
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.28.38/loginSignup/update.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (response.equals("Success")) {
                                    Toast.makeText(Student_details.this, "Record Update", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.getLocalizedMessage());
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("registrationNo", registration_no);
                        paramV.put("fullName", full_name);
                        paramV.put("rollno", roll_no);
                        paramV.put("email", email);
                        paramV.put("branch", branch);
                        paramV.put("phoneNo", phone_no);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                full_name = String.valueOf(Full_name.getText());
                roll_no = String.valueOf(Roll_no.getText());
                email = String.valueOf(Email.getText());
                branch = String.valueOf(Branch.getText());
                phone_no = String.valueOf(Phone_no.getText());
                registration_no = String.valueOf(Registration_no.getText());
                password = String.valueOf(Passwords.getText());
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                String url = "http://192.168.28.38/loginSignup/delete.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (response.equals("Success")) {
                                    Toast.makeText(Student_details.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.getLocalizedMessage());
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("registrationNo", registration_no);
                        paramV.put("fullName", full_name);
                        paramV.put("rollno", roll_no);
                        paramV.put("branch", branch);
                        paramV.put("email", email);
                        paramV.put("phoneNo", phone_no);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
}