package com.icos.podarmills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SupplyChainMainPage extends AppCompatActivity {
    Button addOrder,viewOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_chain_main_page);

        addOrder=findViewById(R.id.add_order_button);
        viewOrders=findViewById(R.id.view_orders_button);

        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupplyChainMainPage.this,SupplyChainAddOrder.class));
            }
        });
        viewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupplyChainMainPage.this,SupplyChainOrderList.class));
            }
        });
    }
}
