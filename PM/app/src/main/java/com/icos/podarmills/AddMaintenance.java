package com.icos.podarmills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddMaintenance extends AppCompatActivity {
    Button sDate;
    CalendarView cv;
    String selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_maintenance);
        sDate=findViewById(R.id.SaveDateB);
        cv=findViewById(R.id.calendarView);

        sDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore.getInstance().collection("inventory").document(getIntent().getStringExtra("inventoryID")).update("date",selectedDate);
                Intent i=new Intent(AddMaintenance.this,InventoryDetails.class);
                i.putExtra("inventoryID",getIntent().getStringExtra("inventoryID"));
                Toast.makeText(getApplicationContext(),"Added date "+selectedDate,Toast.LENGTH_SHORT).show();
                startActivity(i);

            }
        });


        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                selectedDate=String.valueOf(i)+"-"+String.valueOf(i1+1)+"-"+String.valueOf(i2);
            }
        });





    }
}
