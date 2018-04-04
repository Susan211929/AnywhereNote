package com.notes.anywherenote.anywherenote;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by susan_000 on 04-Mar-18.
 */

public class CreateChecklist extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        ImageView add = (ImageView) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(CreateChecklist.this);
                View add_chcklst = getLayoutInflater().inflate(R.layout.add_checklist,null);
                EditText input = (EditText) add_chcklst.findViewById(R.id.txt_edt);
                Button add_btn = (Button) add_chcklst.findViewById(R.id.btn_add);
                add_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CreateChecklist.this, "Yaaaaayyyyy!", Toast.LENGTH_SHORT).show();
                    }
                });
                adb.setView(add_chcklst);
                adb.create().show();
            }
        });
    }
}