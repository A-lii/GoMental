package com.example.gomental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class CallTextActivity extends AppCompatActivity {

    private Button callTextButton;
    private Database database;

    private static final String HELPLINE_NUMBER = "0000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_text);

        database = new Database(this, "GoMental", null, 1);
/*
        Button callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the call details
                String username = "John Doe"; // Replace with the actual username
                String date = getCurrentDate(); // Implement getCurrentDate() to get the current date
                String time = getCurrentTime(); // Implement getCurrentTime() to get the current time
                String duration = "00:05:00"; // Replace with the actual call duration

                // Save the call details to the database
                database.addCall(username, date, time, duration);
            }
        });

        */

        Button callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(HELPLINE_NUMBER);
            }
        });

        Button textButton = findViewById(R.id.textButton);
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendText(HELPLINE_NUMBER);
            }
        });
    }

    private void makeCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    private String getCurrentTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date currentTime = new Date();
        return timeFormat.format(currentTime);
    }

    private void sendText(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        startActivity(intent);
    }
}
