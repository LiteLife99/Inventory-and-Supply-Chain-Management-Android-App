package com.icos.podarmills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class InventoryDetails extends AppCompatActivity {
    String inventoryID;
    TextView name,date,description;
    Button addMaintenance,removeInventory;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_details);
        name=findViewById(R.id.InventoryOutputTV);
        description=findViewById(R.id.DescriptionOutput);
        date=findViewById(R.id.DateOutput);
        addMaintenance=findViewById(R.id.AddMaintenanceB);
        removeInventory=findViewById(R.id.RemoveInventoryB);
        checkBox=findViewById(R.id.checkBox);
        inventoryID=getIntent().getStringExtra("inventoryID");
        FirebaseFirestore.getInstance().collection("inventory").document(inventoryID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                name.setText(documentSnapshot.getId());
                if(documentSnapshot.get("date")!=null)
                date.setText(documentSnapshot.get("date").toString());
                if(documentSnapshot.get("description")!=null)
                    description.setText(documentSnapshot.get("description").toString());
            }
        });




        addMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(InventoryDetails.this,AddMaintenance.class);
                i.putExtra("inventoryID",inventoryID);
                startActivity(i);
            }
        });

        removeInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore.getInstance().collection("inventory").document(inventoryID).delete();
                startActivity(new Intent(InventoryDetails.this,InventoryList.class));
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked())
                    FirebaseFirestore.getInstance().collection("inventory").document(inventoryID).update("date","");
            }
        });


    }
}
