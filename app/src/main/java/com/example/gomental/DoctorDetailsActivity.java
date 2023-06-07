package com.example.gomental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 =
            {
                    {"Doctor Name: Prof. Brig. Gen. Dr. Kumrul Hasan", "Hospital Address: Combined Military Hospital, Dhaka", "Experience: 5 years", "Contact No.: +8809666700100", "1000"},
                    {"Doctor Name: Dr. Mekhala Sarkar", "Hospital Address: National Institute of Mental Health & Hospital", "Experience: 12 years", "Contact No.: +8801611216232", "2000"},
                    {"Doctor Name: Dr. Nigar Sultana", "Hospital Address: Evercare Hospital Dhaka ", "Experience: 2 years", "Contact No.: 10678", "3000"},
                    {"Doctor Name: Dr. Arman Ibne Haq", "Hospital Address: Bangladesh Medical College & Hospital", "Experience: 3 years", "Contact No.: +8801715622916 ", "4000"},
                    {"Doctor Name: Prof. Dr. Nurun Nahar Chowdhury", "Hospital Address: Green Life Medical College & Hospital", "Experience: 6 years", "Contact No.: +8801711907659", "2000"},
            };
    private String[][] doctor_details2 =
            {
                    {"Doctor Name: Dr. Waziul Alam Chowdhury", "Hospital Address: Square Hospital", "Experience: 5 years", "Contact No.: +8809610010616", "3000"},
                    {"Doctor Name: Ms. Sharmin Haque", "Hospital Address: Square Hospital", "Experience: 6 years", "Contact No.: +8809610010616", "2000"},
                    {"Doctor Name: Ms. Sharmin akter", "Hospital Address: Evercare Hospital Dhaka", "Experience: 3 years", "Contact No.: +8809666710678", "4000"},
                    {"Doctor Name: Ms. Fabia Alam", "Hospital Address: Evercare Hospital Dhaka", "Experience: 4 years", "Contact No.: +8809666710678", "3000"},
                    {"Doctor Name: Ms. Tarana Anis", "Hospital Address: Evercare Hospital Dhaka", "Experience: 2 years", "Contact No.: +8809666710678", "2500"},
            };
    private String[][] doctor_details3 =
            {
                    {"Doctor Name: Prof. Dr. Mohammad S I Mullick", "Hospital Address: Bangladesh Psychiatric Care, Shimanto Shambhar", "Experience: 7 years", "Contact No.: 01872-863002", "1000"},
                    {"Doctor Name: Dr Anwara Begum", "Hospital Address: Rupayan Legend", "Experience: 3 years", "Contact No.: 01715-228152", "1000"},
                    {"Doctor Name: Dr. Towhidul Islam", "Hospital Address: Dhaka Monorog Clinic", "Experience: 2 years", "Contact No.: 01750-693956", "1000"},
                    {"Doctor Name: Md. Mehedi Hasan", "Hospital Address: Level 7 (Moar), Holding 02, Shaptak Square ", "Experience: 9 years", "Contact No.: 01715-703260", "1000"},
            };
    private String[][] doctor_details4 =
            {
                    {"Doctor Name: Kamrul Hasan (Mind Trainer and Corporate Counselor)", "Hospital Address: Bangladesh Psychiatric Association", "Experience: 3 years", "Contact No.: 01743-039146", "1000"},
                    {"Doctor Name: Dr. Zakiul Abrar (CBT Therapist & Life Coach)", "Hospital Address: Therapy Route", "Experience: 5 years", "Contact No.: 01715092302", "3000"},
                    {"Doctor Name: Sumaia Azmi (Wellness Coach)", "Hospital Address: Nirvana ", "Experience: 4 years", "Contact No.: ", "4000"},
                    {"Doctor Name: Amrito Mondol (Career Coach)", "Hospital Address: Bangladesh Psychiatric Association ", "Experience: 10 years", "Contact No.: 01325783456", "2000"},
                    {"Doctor Name: Usmania Hamid (Anxiety Counselor) ", "Hospital Address: BtterHelp Association", "Experience: 2 years", "Contact No.: 01757623456", "3000"},
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
        btn = findViewById(R.id.buttonDDBack);

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
        ListView lst = findViewById(R.id.listviewDD);
        lst.setAdapter(sa);
  }
}