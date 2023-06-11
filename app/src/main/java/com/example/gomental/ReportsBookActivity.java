package com.example.gomental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReportsBookActivity extends AppCompatActivity {

    EditText edname, edaddress,edcontact,edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_book);

        edname = findViewById(R.id.editTextRBFullName);
        edaddress = findViewById(R.id.editTextRBAddress);
        edcontact = findViewById(R.id.editTextRBContact);
        edpincode = findViewById(R.id.editTextRBPincode);
        btnBooking = findViewById(R.id.buttonRBBooking);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").split(":");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

            btnBooking.setOnClickListener(v ->  {
                    SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    String username = sharedpreferences.getString("username", "");

                    try (Database db = new Database(getApplicationContext(), "GoMental", null, 1)) {
                        db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(), Integer.parseInt(edpincode.getText().toString()), date, time, Float.parseFloat(price[1]), "reports");
                        db.removeCart(username, "reports");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Your booking is done successfully.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ReportsBookActivity.this, HomeActivity.class));

            });

    }
}