package com.example.gomental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        //CardView exit = findViewById(R.id.cardFDBack);
        //exit.setOnClickListener (new View.OnClickListener() {
        // @Override
        // public void onClick(View v) {
        //   SharedPreferences.Editor editor = sharedpreferences.edit();
        //   editor.clear();
        //   editor .apply();
        //   startActivity (new Intent( FindDoctorActivity.this, HomeActivity.class));
        //  }
        // });

        CardView psychiatrist = findViewById(R. id. cardFDPsychiatrist);
        psychiatrist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent( FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Psychiatrist");
                startActivity(it);
            }
        });

        CardView psychologist = findViewById(R. id. cardFDPsychologist);
        psychologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent( FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Psychologist");
                startActivity(it);
            }
        });

        CardView childPsychiatrist = findViewById(R. id. cardFDChildPsychiatrist);
        childPsychiatrist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent( FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Child Psychiatrist");
                startActivity(it);
            }
        });

        CardView counselor = findViewById(R. id. cardFDCounselor);
        counselor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent( FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Counselor");
                startActivity(it);

            }
        });

    }
}