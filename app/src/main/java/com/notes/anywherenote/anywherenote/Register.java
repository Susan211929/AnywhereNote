package com.notes.anywherenote.anywherenote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by susan_000 on 14-Apr-18.
 */

public class Register extends Activity {

    private FirebaseAuth mAuth;
    private EditText username,pass1,pass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_pg);
        mAuth=FirebaseAuth.getInstance();
        username = (EditText) findViewById(R.id.email_ip);
        pass1 = (EditText) findViewById(R.id.pass_ip1);
        pass2 = (EditText) findViewById(R.id.pass_ip2);
        Button reg = (Button) findViewById(R.id.reg_btn);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailValid(username) && checkPassword(pass1,pass2)) {
                    mAuth.createUserWithEmailAndPassword(username.getText().toString(), pass1.getText().toString()).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.i("success", "User succesfully signed in");
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(Register.this,MainActivity.class));
                                finish();
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean emailValid(EditText email) {
        String email_input = email.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(email_input.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please provide email address", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (email_input.matches(emailPattern))
            return true;
        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
        return false;
    }

    private boolean checkPassword(EditText pass1, EditText pass2) {
        if(pass1.getText().toString().equals(""))
        {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(pass2.getText().toString().equals(""))
        {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (pass1.getText().toString().equals(pass2.getText().toString()))
            return true;
        Toast.makeText(Register.this, "Password fields don't match", Toast.LENGTH_SHORT).show();
        return false;
    }
}
