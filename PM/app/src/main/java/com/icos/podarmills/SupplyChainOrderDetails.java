package com.icos.podarmills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class SupplyChainOrderDetails extends AppCompatActivity {
    TextView orderNumber, itemDetails, userDetails, status, price;
    Button changeStatus;
    String orderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_chain_order_details);
        orderNumber = findViewById(R.id.OrderNumberOutput);
        itemDetails = findViewById(R.id.ItemDetailsOutput);
        userDetails = findViewById(R.id.UserDetailsOutput);
        status = findViewById(R.id.StatusOutput);
        price = findViewById(R.id.PriceOutput);
        changeStatus = findViewById(R.id.button);


        if(getIntent().getStringExtra("type").equals("admin"))
        {
            changeStatus.setVisibility(View.VISIBLE);
            changeStatus.setEnabled(true);
            FirebaseFirestore.getInstance().collection("orders").document(getIntent().getStringExtra("orderID")).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    orderNumber.setText(documentSnapshot.get("orderNumber").toString());
                    itemDetails.setText(documentSnapshot.get("itemDetails").toString());
                    userDetails.setText(documentSnapshot.get("userEmail").toString());
                    status.setText(documentSnapshot.get("status").toString());
                    price.setText(documentSnapshot.get("price").toString());
                    orderID=documentSnapshot.getId();

                }
            });
        }
        if(getIntent().getStringExtra("type").equals("client")) {
            changeStatus.setVisibility(View.INVISIBLE);
            changeStatus.setEnabled(false);
            FirebaseFirestore.getInstance().collection("orders").whereEqualTo("userEmail", MainActivity.emailHead).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    DocumentSnapshot documentSnapshot=queryDocumentSnapshots.getDocuments().get(0);
                    orderNumber.setText(documentSnapshot.get("orderNumber").toString());
                    itemDetails.setText(documentSnapshot.get("itemDetails").toString());
                    userDetails.setText(documentSnapshot.get("userEmail").toString());
                    status.setText(documentSnapshot.get("status").toString());
                    price.setText(documentSnapshot.get("price").toString());
                    orderID=documentSnapshot.getId();


                }
            });
        }





        changeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SupplyChainOrderDetails.this,SupplyChainStatusChange.class);
                i.putExtra("orderID",orderID);
                startActivity(i);
            }
        });

    }
}