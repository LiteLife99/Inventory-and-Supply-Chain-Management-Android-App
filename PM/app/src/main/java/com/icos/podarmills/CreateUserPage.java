package com.icos.podarmills;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.icos.podarmills.R;

import java.util.HashMap;

public class CreateUserPage extends AppCompatActivity {
    EditText name,phonenumber,username,password;
    Button createUser;
    RadioButton RadioselectedButton;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_page);
        radioGroup=findViewById(R.id.radio_group_id2);
        username=findViewById(R.id.username2);
        password=findViewById(R.id.password2);
        name=findViewById(R.id.name_id);
        phonenumber=findViewById(R.id.mobile);
        createUser=findViewById(R.id.create_user);
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(username.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Signup Successful",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(CreateUserPage.this, MainActivity.class));
                            RadioselectedButton=findViewById(radioGroup.getCheckedRadioButtonId());

                            HashMap<String,Object> hashMap=new HashMap();
                            hashMap.put("type",RadioselectedButton.getText().toString());
                            hashMap.put("name",name.getText().toString());
                            hashMap.put("phonenumber",phonenumber.getText().toString());
                            FirebaseFirestore.getInstance().collection("users").document(username.getText().toString()).set(hashMap);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Signup failed, something went wrong. Try again.",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });
    }
}
