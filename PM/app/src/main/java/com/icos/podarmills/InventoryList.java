package com.icos.podarmills;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

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

        FirebaseFirestore.getInstance().collection("inventory").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                queryDocumentSnapshots.getDocuments().forEach(new Consumer<DocumentSnapshot>() {
                    @Override
                    public void accept(DocumentSnapshot documentSnapshot) {
                        inventoryNames.add(documentSnapshot.getId());
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        addInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InventoryList.this,AddInventoryActivity.class));
            }
        });

        inventoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(InventoryList.this,InventoryDetails.class);
                intent.putExtra("inventoryID",inventoryNames.get(i));
                startActivity(intent);
            }
        });



    }
}
