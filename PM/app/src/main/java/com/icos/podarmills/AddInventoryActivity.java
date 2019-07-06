package com.icos.podarmills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AddInventoryActivity extends AppCompatActivity {

    private EditText edtInventoryName;
    private EditText edtInventoryDescription;
    private EditText edtMaintenanceDate;
    private Button addNewInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);

        edtInventoryName = findViewById(R.id.edtInventoryName);
        edtInventoryDescription = findViewById(R.id.edtInventoryDescription);
        edtMaintenanceDate = findViewById(R.id.edtMaintenanceDate);
        addNewInventory = findViewById(R.id.addNewInventory);

        addNewInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("description",edtInventoryDescription.getText().toString());
                hashMap.put("maintenanceDate",edtMaintenanceDate.getText().toString());

                FirebaseFirestore.getInstance().collection("inventory").document(edtInventoryName.getText().toString()).set(hashMap);
                startActivity(new Intent(new Intent(AddInventoryActivity.this,InventoryList.class)));
            }
        });


    }
}
