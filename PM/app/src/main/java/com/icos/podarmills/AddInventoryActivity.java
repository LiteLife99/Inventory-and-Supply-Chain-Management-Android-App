package com.icos.podarmills;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

        //Ajinkya add these details to firebase then using that, update the ListView in InventoryList activity

    }
}
