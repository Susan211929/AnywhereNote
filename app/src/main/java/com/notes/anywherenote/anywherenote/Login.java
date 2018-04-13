package com.notes.anywherenote.anywherenote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by susan_000 on 14-Apr-18.
 */

public class Login extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText username = (EditText) findViewById(R.id.email_ip);
        EditText pass = (EditText) findViewById(R.id.pass_ip);
        Button login = (Button) findViewById(R.id.log_btn);
        TextView register = (TextView) findViewById(R.id.sign_up);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });
    }
}
