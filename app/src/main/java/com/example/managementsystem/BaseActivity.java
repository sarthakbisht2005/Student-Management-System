package com.example.managementsystem;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

public class BaseActivity extends Profile_Screen {
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.home);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.profile) {
                    Intent intent = new Intent(BaseActivity.this, Profile_Screen.class);
                    startActivity(intent);
                } else if (itemId == R.id.gallery) {
                    Intent intent = new Intent(BaseActivity.this, Gallery.class);
                    startActivity(intent);
                } else if (itemId == R.id.student) {
                    Toast.makeText(getApplicationContext(), "You have to login admin panel to see Student details", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BaseActivity.this, Admin_login.class);
                    startActivity(intent);
                } else if (itemId == R.id.result) {
                    Intent intent = new Intent(BaseActivity.this, Result_Screen.class);
                    startActivity(intent);
                } else if (itemId == R.id.about_us) {
                    Intent intent = new Intent(BaseActivity.this, Aboutus.class);
                    startActivity(intent);
                } else if (itemId == R.id.logout) {
                    logoutMenu();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logoutMenu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Log out of your account ?");
        builder.setIcon(R.drawable.logo_round);
        builder.setPositiveButton("LOG OUT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.28.38/loginSignup/logout.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (response.equals("success")) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("logged", "");
                                    editor.putString("registrationNo", "");
                                    editor.putString("fullName", "");
                                    editor.putString("email", "");
                                    editor.putString("branch", "");
                                    editor.putString("rollno", "");
                                    editor.putString("phoneNo", "");
                                    editor.putString("apiKey", "");
                                    editor.apply();
                                    Intent intent = new Intent(getApplicationContext(), Login_Screen.class);
                                    startActivity(intent);
                                    finish();
                                } else
                                    Toast.makeText(BaseActivity.this, response, Toast.LENGTH_SHORT).show();

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

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setTextColor(ContextCompat.getColor(BaseActivity.this, android.R.color.holo_red_dark));

                Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                negativeButton.setTextColor(ContextCompat.getColor(BaseActivity.this, android.R.color.black));
            }
        });

        dialog.show();

    }

}