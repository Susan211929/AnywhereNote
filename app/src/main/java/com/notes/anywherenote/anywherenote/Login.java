package com.notes.anywherenote.anywherenote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Login extends Activity{
    private FirebaseAuth mAuth;
    private EditText username,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.email_ip);
        pass = (EditText) findViewById(R.id.pass_ip);
        Button login = (Button) findViewById(R.id.log_btn);
        TextView register = (TextView) findViewById(R.id.sign_up);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null)
            startActivity(new Intent(Login.this,MainActivity.class));

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkMail(username) && checkPassword(pass)) {
                    mAuth.signInWithEmailAndPassword(username.getText().toString(), pass.getText().toString()).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(Login.this, MainActivity.class));
                                finish();
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

            private boolean checkMail(EditText username) {
                String email_input = username.getText().toString().trim();
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

            private boolean checkPassword(EditText password){
                if(password.getText().toString().equals(""))
                {
                    Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }
        });
    }
}
