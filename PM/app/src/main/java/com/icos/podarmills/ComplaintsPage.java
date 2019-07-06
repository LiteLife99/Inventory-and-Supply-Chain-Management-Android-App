package com.icos.podarmills;

import android.content.pm.PackageInstaller;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.function.Consumer;

public class ComplaintsPage extends AppCompatActivity {
    Spinner spinner;
    ArrayList<String> names=new ArrayList<>(),emailids=new ArrayList<>();
    ArrayAdapter arrayAdapter;
    Button submitComplaint;
    EditText editText;
    String complaint,reid,seid,spwd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_page);
        editText=findViewById(R.id.complaints_edit_text);
        submitComplaint=findViewById(R.id.submit_complaint);
        FirebaseFirestore.getInstance().collection("employees").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                queryDocumentSnapshots.getDocuments().forEach(new Consumer<DocumentSnapshot>() {
                    @Override
                    public void accept(DocumentSnapshot documentSnapshot) {
                        names.add(documentSnapshot.get("name").toString());
                        emailids.add(documentSnapshot.get("email").toString());
                        if(arrayAdapter!=null)
                            arrayAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        spinner=findViewById(R.id.spinner_id);
        arrayAdapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,names);
        spinner.setAdapter(arrayAdapter);


        submitComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaint=editText.getText().toString();
                reid=emailids.get(spinner.getSelectedItemPosition());
                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("complaint",complaint);
                hashMap.put("email",reid);
                FirebaseFirestore.getInstance().collection("complaints").add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Complaint submitted successfully",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }


}
