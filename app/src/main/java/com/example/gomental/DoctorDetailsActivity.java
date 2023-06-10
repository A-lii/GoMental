package com.example.gomental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 =
            {
                    {"Doctor Name: Prof. Brig. Gen. Dr. Kumrul Hasan", "Address: Combined Military Hospital, Dhaka", "Exp: 5yrs", "Mobile: 09666700100", "1000"},
                    {"Doctor Name: Dr. Mekhala Sarkar", "Address: National Institute of Mental Health & Hospital", "Exp: 12yrs", "Mobile: 01611216232", "2000"},
                    {"Doctor Name: Dr. Nigar Sultana", "Address: Evercare Hospital Dhaka ", "Exp: 2yrs", "Mobile: 10678", "3000"},
                    {"Doctor Name: Dr. Arman Ibne Haq", "Address: Bangladesh Medical College & Hospital", "Exp: 3yrs", "Mobile: 01715622916 ", "4000"},
                    {"Doctor Name: Prof. Dr. Nurun Nahar Chowdhury", "Address: Green Life Medical College & Hospital", "Exp: 6yrs", "Mobile: 01711907659", "2000"},
            };
    private String[][] doctor_details2 =
            {
                    {"Doctor Name: Dr. Waziul Alam Chowdhury", "Address: Square Hospital", "Exp: 5yrs", "Mobile: 09610010616", "3000"},
                    {"Doctor Name: Ms. Sharmin Haque", "Address: Square Hospital", "Exp: 6yrs", "Mobile: 09610010616", "2000"},
                    {"Doctor Name: Ms. Sharmin akter", "Address: Evercare Hospital Dhaka", "Exp: 3yrs", "Mobile: 09666710678", "4000"},
                    {"Doctor Name: Ms. Fabia Alam", "Address: Evercare Hospital Dhaka", "Exp: 4yrs", "Mobile: 09666710678", "3000"},
                    {"Doctor Name: Ms. Tarana Anis", "Address: Evercare Hospital Dhaka", "Exp: 2yrs", "Mobile: 09666710678", "2500"},
            };
    private String[][] doctor_details3 =
            {
                    {"Doctor Name: Prof. Dr. Mohammad S I Mullick", "Address: Bangladesh Psychiatric Care, Shimanto Shambhar", "Exp: 7yrs", "Mobile: 01872863002", "1000"},
                    {"Doctor Name: Dr Anwara Begum", "Address: Rupayan Legend", "Exp: 3yrs", "Mobile: 01715-228152", "1000"},
                    {"Doctor Name: Dr. Towhidul Islam", "Address: Dhaka Monorog Clinic", "Exp: 2yrs", "Mobile: 01750-693956", "1000"},
                    {"Doctor Name: Md. Mehedi Hasan", "Address: Level 7 (Moar), Holding 02, Shaptak Square ", "Exp: 9yrs", "Mobile: 01715-703260", "1000"},
            };
    private String[][] doctor_details4 =
            {
                    {"Doctor Name: Kamrul Hasan (Mind Trainer and Corporate Counselor)", "Hospital Address: Bangladesh Psychiatric Association", "Exp: 3yrs", "Contact No.: 01743-039146", "1000"},
                    {"Doctor Name: Dr. Zakiul Abrar (CBT Therapist & Life Coach)", "Hospital Address: Therapy Route", "Exp: 5yrs", "Contact No.: 01715092302", "3000"},
                    {"Doctor Name: Sumaia Azmi (Wellness Coach)", "Hospital Address: Nirvana ", "Exp: 4yrs", "Contact No.: ", "4000"},
                    {"Doctor Name: Amrito Mondol (Career Coach)", "Hospital Address: Bangladesh Psychiatric Association ", "Exp: 1yrs", "Contact No.: 01325783456", "2000"},
                    {"Doctor Name: Usmania Hamid (Anxiety Counselor) ", "Hospital Address: BtterHelp Association", "Exp: 2yrs", "Contact No.: 01757623456", "3000"},
            };

    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonReportsGoToCart);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);


        if(title.compareTo("Psychiatrist")==0)
            doctor_details = doctor_details1;
        else if(title.compareTo("Psychologist")==0)
            doctor_details = doctor_details2;
        else if(title.compareTo("Child Psychiatrist")==0)
            doctor_details = doctor_details3;
        else if(title.compareTo("Counselor")==0)
            doctor_details = doctor_details4;

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i = 0; i<doctor_details.length; i++){
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fees"+doctor_details[i][4]+ "/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                 R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
                );

        ListView lst = findViewById(R.id.listviewReports);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent( DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2" ,doctor_details[i][0]);
                it.putExtra("text3", doctor_details [i][1]);
                it.putExtra("text4", doctor_details [i][3]);
                it.putExtra("text5", doctor_details [i][4]);
                startActivity(it);
            }
        });
  }
}