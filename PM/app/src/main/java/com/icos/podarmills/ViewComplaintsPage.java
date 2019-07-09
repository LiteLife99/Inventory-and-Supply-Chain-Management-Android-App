package com.icos.podarmills;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    }
}
