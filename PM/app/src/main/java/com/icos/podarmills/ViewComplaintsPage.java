package com.icos.podarmills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewComplaintsPage extends AppCompatActivity {

    private TextView txtViewComplaints;
    private Button btnWriteComplaint;
    private ListView complaintsList;
    private ArrayList<String> complaintNames;
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints_page);

        txtViewComplaints = findViewById(R.id.txtViewComplaint);
        btnWriteComplaint = findViewById(R.id.writeComplaint);
        complaintsList = (ListView) findViewById(R.id.complaintListView);
        complaintNames = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, complaintNames);
        complaintsList.setAdapter(adapter);

        btnWriteComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewComplaintsPage.this,ComplaintsPage.class));
            }
        });



    }
}
