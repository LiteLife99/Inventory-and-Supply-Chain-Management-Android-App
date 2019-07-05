package com.icos.podarmills;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SupplyChainAddOrder extends AppCompatActivity {
    EditText orderNumber,itemDetails,userDetails,status,price;
    Button addOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_chain_add_order);
        orderNumber=findViewById(R.id.OrderNumberOutput2);
        itemDetails=findViewById(R.id.ItemDetailsOutput2);
        userDetails=findViewById(R.id.UserDetailsOutput2);
        status=findViewById(R.id.StatusOutput2);
        price=findViewById(R.id.PriceOutput2);
        addOrder=findViewById(R.id.add_order_button);

        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> hm=new HashMap<>();
                hm.put("orderNumber",orderNumber.getText().toString());
                hm.put("itemDetails",itemDetails.getText().toString());
                hm.put("userEmail",itemDetails.getText().toString());
                hm.put("status",status.getText().toString());
                hm.put("price",price.getText().toString());
                FirebaseFirestore.getInstance().collection("orders").add(hm);
            }
        });


    }
}
