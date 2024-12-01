package com.example.managementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_Screen extends AppCompatActivity {

    Spinner sp;
    FloatingActionButton fab;
    EditText Email;
    EditText Password;
    Button loginButton;
    TextView adminlogin;
    TextView signup;

    String full_name, email, branch, roll_no, phone_no, registration_no, password, apiKey;
    TextView textViewError;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_screen);

        sp = findViewById(R.id.sp);
        String[] sk = getResources().getStringArray(R.array.loginSelection);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this, R.array.loginSelection, android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);

        fab = findViewById(R.id.fab);
        fab.setSelected(true);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login_Screen.this.finish();
                System.exit(0);
            }
        });

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);

        adminlogin = findViewById(R.id.adminlogin);
        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Admin_login.class);
                startActivity(intent);
                finish();
            }
        });

        signup = findViewById(R.id.signup);
        signup.setSelected(true);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup_Screen.class);
                startActivity(intent);
                finish();
            }
        });

        textViewError = findViewById(R.id.error);
        progressBar = findViewById(R.id.loading);
        sharedPreferences = getSharedPreferences("MyappName", MODE_PRIVATE);

        if (sharedPreferences.getString("logged", "false").equals("true")) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        loginButton = findViewById(R.id.loginButton);
        loginButton.setSelected(true);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewError.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                branch = sp.getSelectedItem().toString();
                email = String.valueOf(Email.getText());
                password = String.valueOf(Password.getText());
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.28.38/loginSignup/login.php";


                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String status = jsonObject.getString("status");
                                    String message = jsonObject.getString("message");
                                    if (status.equals("success")) {
                                        registration_no = jsonObject.getString("registrationNo");
                                        full_name = jsonObject.getString("fullName");
                                        email = jsonObject.getString("email");
                                        branch = jsonObject.getString("branch");
                                        phone_no = jsonObject.getString("phoneNo");
                                        roll_no = jsonObject.getString("rollno");
                                        apiKey = jsonObject.getString("apiKey");
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("logged", "true");
                                        editor.putString("registrationNo", registration_no);
                                        editor.putString("fullName", full_name);
                                        editor.putString("email", email);
                                        editor.putString("branch", branch);
                                        editor.putString("phoneNo", phone_no);
                                        editor.putString("rollno", roll_no);
                                        editor.putString("apiKey", apiKey);
                                        editor.apply();
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        textViewError.setText(error.getLocalizedMessage());
                        textViewError.setVisibility(View.VISIBLE);
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("branch", branch);
                        paramV.put("email", email);
                        paramV.put("password", password);
                        return paramV;
                    }
                };
                queue.add(stringRequest);

            }

        });
    }

}


