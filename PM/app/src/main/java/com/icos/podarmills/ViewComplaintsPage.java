package com.icos.podarmills;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ViewComplaintsPage extends AppCompatActivity {

    private TextView txtViewComplaints;
    private Button btnWriteComplaint;
    private ListView complaintsList;
    private ArrayList<String> complaintNames;
    private ArrayList<String> complaintIds;
    private ArrayAdapter adapter;
    private int numComps=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints_page);
        complaintIds=new ArrayList<>();
        txtViewComplaints = findViewById(R.id.txtViewComplaint);
        btnWriteComplaint = findViewById(R.id.writeComplaint);
        complaintsList = (ListView) findViewById(R.id.complaintListView);
        complaintNames = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, complaintNames);
        complaintsList.setAdapter(adapter);

        complaintsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FirebaseFirestore.getInstance().collection("complaints").document(complaintIds.get(i)).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        txtViewComplaints.setText(documentSnapshot.get("complaint").toString());
                    }
                });

            }
        });

        btnWriteComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewComplaintsPage.this,ComplaintsPage.class));
            }
        });

        FirebaseFirestore.getInstance().collection("complaints").whereEqualTo("email",MainActivity.emailHead).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                queryDocumentSnapshots.getDocuments().forEach(new Consumer<DocumentSnapshot>() {
                    @Override
                    public void accept(DocumentSnapshot documentSnapshot) {
                        numComps++;
                        complaintNames.add("Complaint number "+numComps);
                        complaintIds.add(documentSnapshot.getId());
                        if(adapter!=null)
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });





    }
}
