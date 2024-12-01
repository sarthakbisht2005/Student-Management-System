package com.example.managementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Admin_login extends AppCompatActivity {

    FloatingActionButton fab;
    EditText Username;
    EditText Password;
    Button loginButton;
    TextView textViewError;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_login);

        fab = findViewById(R.id.fab);
        fab.setSelected(true);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Admin_login.this.finish();
                System.exit(0);
            }
        });

        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUsername();
                if (Username.getText().toString().equals("username") && Password.getText().toString().equals("sarthak@123")) {
                    Intent intent = new Intent(getApplicationContext(), Student_details.class);
                    startActivity(intent);
                    finish();

                    Toast.makeText(Admin_login.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Admin_login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    boolean isRegs(EditText text) {
        CharSequence regs_no = text.getText().toString();
        return (!TextUtils.isEmpty(regs_no));
        // && Patterns.PHONE.matcher(regs_no).matches()
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void checkUsername() {
        if (isEmpty(Username)) {
            Username.setError("You must enter Registration no to login!");
        } else {
            if (!isRegs(Username)) {
                Username.setError("Enter valid registration no!");
            }
        }

        if (isEmpty(Password)) {
            Password.setError("You must enter password to login!");
        } else {
            if (Password.getText().toString().length() < 4) {
                Password.setError("Password must be at least 4 chars long!");
            }
        }

    }

}