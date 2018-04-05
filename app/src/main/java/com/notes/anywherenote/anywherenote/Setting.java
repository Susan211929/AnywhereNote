package com.notes.anywherenote.anywherenote;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by susan_000 on 05-Apr-18.
 */

public class Setting extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*To check email id is valid*/
                EditText emailValidate = (EditText)findViewById(R.id.edt_eid);
                String email = emailValidate.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (email.matches(emailPattern))
                {
                    Intent data = new Intent();
                    data.setData(Uri.parse(emailValidate.getText().toString()));
                    setResult(RESULT_OK, data);
                    finish();
                }
                else
                        Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
