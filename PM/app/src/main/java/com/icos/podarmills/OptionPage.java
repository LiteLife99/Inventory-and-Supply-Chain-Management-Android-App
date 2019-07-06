package com.icos.podarmills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.icos.podarmills.R;

public class OptionPage extends AppCompatActivity {
    private Button inventoryButton, supplyButton, complaintsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_page);

        inventoryButton = findViewById(R.id.inventoryButton);
        supplyButton = findViewById(R.id.supplyButton);
        complaintsButton = findViewById(R.id.complaintsButton);

        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                transitionToInventoryListActivity();

            }
        });

        supplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OptionPage.this,SupplyChainMainPage.class));
            }
        });

        complaintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OptionPage.this,ComplaintsPage.class));
            }
        });

        }

    private void transitionToInventoryListActivity() {

        Intent intent = new Intent(this, InventoryList.class);
        startActivity(intent);
    }

    }

