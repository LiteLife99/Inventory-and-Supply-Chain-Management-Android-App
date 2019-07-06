package com.icos.podarmills;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class InventoryDetails extends AppCompatActivity {
    String inventoryID;
    TextView name,date,details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_details);
        inventoryID=getIntent().getStringExtra("inevntoryID");
        FirebaseFirestore.getInstance().collection("inevntory").document(inventoryID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                name.setText(documentSnapshot.getId());
                date.setText(documentSnapshot.get("maintenanceDate").toString());
                details.setText(documentSnapshot.get("details").toString());
            }
        });
    }
}
