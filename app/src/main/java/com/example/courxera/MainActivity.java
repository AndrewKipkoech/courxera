package com.example.courxera;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText email, pass;
    Button login, login2;


    String correctEmail = "andrewkip78";
    String correctPass = "126367";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.btnlogin);
        email = findViewById(R.id.editEmail);
        pass = findViewById(R.id.editID);
        login2 = (Button) findViewById(R.id.btnAdmin);

        // login listener for students.
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if its empty.
                if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(pass.getText().toString())){
                    Toast toast = Toast.makeText(MainActivity.this, "Empty fields", Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(25);
                    toast.show();
                }else if(email.getText().toString().equals(correctEmail)){
                    if(pass.getText().toString().equals(correctPass)){
                        Intent intent = new Intent(MainActivity.this, CourseActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast toast = Toast.makeText(MainActivity.this, "Invalid Username/ID", Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(25);
                    toast.show();
                }
            }
        });

        //login listener for admin
        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if its empty.
                if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(pass.getText().toString())){
                    Toast toast = Toast.makeText(MainActivity.this, "Empty fields", Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(25);
                    toast.show();
                }else if(email.getText().toString().equals(correctEmail)){
                    if(pass.getText().toString().equals(correctPass)){
                        Intent intent = new Intent(MainActivity.this, AddCourseActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast toast = Toast.makeText(MainActivity.this, "Invalid Username/ID", Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(25);
                    toast.show();
                }
            }
        });

    }
}
