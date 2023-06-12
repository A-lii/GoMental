package com.example.gomental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.editTextBMBFullName);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edcontact = findViewById(R.id.editTextBMBContact);
        edpincode = findViewById(R.id.editTextBMBPincode);
        btnBooking = findViewById(R.id.buttonBMBBooking);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").split(":");
        String date = intent.getStringExtra("date");

        btnBooking.setOnClickListener(v -> {
            SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String username = sharedpreferences.getString("username", "");

            try (Database db = new Database(getApplicationContext(), "GoMental", null, 1)) {
                db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(), Integer.parseInt(edpincode.getText().toString()), date, "", Float.parseFloat(price[1]), "medicine");
                db.removeCart(username, "medicine");
            } catch (Exception e) {
                e.printStackTrace();
                // Handle any exceptions that might occur during database operations
            }

            Toast.makeText(getApplicationContext(), "Your booking is done successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
        });
    }
}
