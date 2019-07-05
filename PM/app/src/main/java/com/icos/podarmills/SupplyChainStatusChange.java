package com.icos.podarmills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class SupplyChainStatusChange extends AppCompatActivity {
    RadioButton r;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_chain_status_change);
        radioGroup=findViewById(R.id.status_rg);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String status=((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                FirebaseFirestore.getInstance().collection("orders").document(getIntent().getStringExtra("orderID")).update("status",status).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent i=new Intent(SupplyChainStatusChange.this,SupplyChainOrderDetails.class);
                        i.putExtra("orderID",getIntent().getStringExtra("orderID"));
                        i.putExtra("type","admin");
                        startActivity(i);
                    }
                });

            }
        });

    }
}
