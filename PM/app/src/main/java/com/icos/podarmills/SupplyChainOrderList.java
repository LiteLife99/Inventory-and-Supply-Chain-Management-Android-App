package com.icos.podarmills;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SupplyChainOrderList extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_chain_order_list);
        listView=findViewById(R.id.order_list_id);
        FirebaseFirestore.getInstance().collection("orders").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                queryDocumentSnapshots.getDocuments().forEach(new Consumer<DocumentSnapshot>() {
                    @Override
                    public void accept(DocumentSnapshot documentSnapshot) {
                        arrayList.add(documentSnapshot.getId());
                        if(arrayAdapter!=null)
                            arrayAdapter.notifyDataSetChanged();
                    }

                });

            }

        });
        arrayAdapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(SupplyChainOrderList.this,SupplyChainOrderDetails.class);
                intent.putExtra("type","admin");
                intent.putExtra("orderID",((TextView)view).getText().toString());
                startActivity(intent);
            }
        });

    }
}
