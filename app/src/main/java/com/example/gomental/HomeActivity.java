package com.example.gomental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedpreferences = getSharedPreferences ( "shared _prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences. getString( "username", "").toString();

        Toast.makeText(getApplicationContext(), "Welcome" +username, Toast.LENGTH_SHORT).show();

        CardView exit = findViewById(R.id.cardExit);
        exit.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor .apply();

                startActivity (new Intent( HomeActivity.this, LoginActivity.class));
            }
        });

        CardView findDoctor = findViewById(R.id.cardFindDoctor);
        findDoctor.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity (new Intent( HomeActivity.this, FindDoctorActivity.class));

            }
        });

        CardView reportsPage = findViewById(R.id.cardReports);

        reportsPage.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity (new Intent( HomeActivity.this, ReportsActivity.class));


            }
        });

        //orderDetails
        CardView orderDetails = findViewById(R.id.cardOrderDetails);
        orderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, OrderDetailsActivity.class));
            }
        });



        //buyMed

        CardView buyMedicine = findViewById(R.id.cardBuyMedicine);
        buyMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BuyMedicineActivity.class));
            }
        });

        CardView callText = findViewById(R.id.cardCallText);
        callText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CallTextActivity.class));
            }
        });


    }
}