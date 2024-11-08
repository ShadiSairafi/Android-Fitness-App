package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
public EditText userName;
public EditText passwordF;
public Button login;
public Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.edittextU);
        passwordF = findViewById(R.id.edittextP);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        List<Users> users = new ArrayList<>();


        signup.setOnClickListener(e ->{
            String usernameS = userName.getText().toString().trim();
            String passwordS = passwordF.getText().toString().trim();
            if (usernameS.isEmpty() || passwordS.isEmpty()){
                Toast.makeText(MainActivity.this,"Please fill the required fields",Toast.LENGTH_SHORT).show();
            }
            else new AlertDialog.Builder(MainActivity.this)
                .setTitle("Confirm Sign Up")
            .setMessage("Are you sure you want to sign up?")
            .setPositiveButton("Yes", (dialog, which) -> {
                users.add(new Users(usernameS,passwordS));
                emptyFields();
                Toast.makeText(MainActivity.this, "Signed up 😊", Toast.LENGTH_SHORT).show();
        }).setNegativeButton("No", (dialog, which) -> {
                dialog.dismiss();
            }).show();});


        login.setOnClickListener(e -> {
            String usernameS = userName.getText().toString().trim();
            String passwordS = passwordF.getText().toString().trim();

            if (usernameS.isEmpty() || passwordS.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill the required fields", Toast.LENGTH_SHORT).show();
            } else if (!usernameS.isEmpty() && !passwordS.isEmpty()) {
                if (users.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Wrong password or username! 😞", Toast.LENGTH_SHORT).show();
                    emptyFields();
                } else {
                    boolean foundUser = false;
                    for (Users user : users) {
                        if (user.getUsername().equals(usernameS) && user.getPassword().equals(passwordS)) {
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                            intent.putExtra("Username", usernameS);
                            emptyFields();
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "Logged In 😊", Toast.LENGTH_SHORT).show();
                            foundUser = true;
                            break;
                        }
                    }
                    if (!foundUser) {
                        emptyFields();
                        Toast.makeText(MainActivity.this, "Wrong password or username! 😞", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
 public void emptyFields(){
     userName.setText("");
     passwordF.setText("");
}
}