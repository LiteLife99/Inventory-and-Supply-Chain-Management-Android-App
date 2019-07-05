package com.icos.podarmills;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class InventoryList extends AppCompatActivity {

    private ListView inventoryList;
    private Button addInventoryButton;
    private ArrayList<String> inventoryNames;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);

        inventoryList = (ListView) findViewById(R.id.inventoryList);
        addInventoryButton = findViewById(R.id.addInventoryButton);
        inventoryNames = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, inventoryNames);
        inventoryList.setAdapter(adapter);

        addInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });



    }
}
