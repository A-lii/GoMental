package com.example.gomental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReportsDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    Button btnAddToCart, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_details);

        tvPackageName = findViewById(R.id.textViewReportsPackageName);
        tvTotalCost = findViewById(R.id.textViewReportsTotalCost);
        edDetails = findViewById(R.id.editTextReportsMultiLine);
        btnAddToCart = findViewById(R.id.buttonReportsAddToCart);
        btnBack = findViewById(R.id.buttonReportsBack2);

        edDetails.setKeyListener(null);

        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost: "+ intent.getStringExtra("text3") + "/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReportsDetailsActivity.this, ReportsActivity.class));
            }
        });

       btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedpreferences.getString("username", "");
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(), "GoMental", null, 1);

                if(db.checkCart(username, product)==1){
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                } else {
                    db.addCart(username,product,price,"reports");
                    Toast.makeText(getApplicationContext(), "Record inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ReportsDetailsActivity.this, ReportsActivity.class));

                }

            }
        });


    }
}