package com.notes.anywherenote.anywherenote;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by susan_000 on 04-Mar-18.
 */

public class CreateText extends Activity {

    private EditText title,body;
    private ImageView done;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        title= (EditText) findViewById(R.id.title);
        body= (EditText) findViewById(R.id.txt_input);
        done= (ImageView) findViewById(R.id.done);
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference("users").child(uid);

        /*To change colour of note*/
        final EditText input = (EditText) findViewById(R.id.txt_input);
        final ImageView colour = (ImageView) findViewById(R.id.select_colour);
        colour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View sel_col = getLayoutInflater().inflate(R.layout.select_colour,(ViewGroup) findViewById(R.id.popUp));
                final PopupWindow pw = new PopupWindow(sel_col, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,true);
                pw.showAtLocation(sel_col, Gravity.CENTER, 0, 0);
                ImageView blue = (ImageView) sel_col.findViewById(R.id.sel_blue);
                ImageView green = (ImageView) sel_col.findViewById(R.id.sel_green);
                ImageView orange = (ImageView) sel_col.findViewById(R.id.sel_orange);
                ImageView red = (ImageView) sel_col.findViewById(R.id.sel_red);
                ImageView purple = (ImageView) sel_col.findViewById(R.id.sel_purple);
                ImageView white = (ImageView) sel_col.findViewById(R.id.sel_white);

                blue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        colour.setBackgroundColor(ContextCompat.getColor(CreateText.this, R.color.blue));
                        input.setBackgroundColor(ContextCompat.getColor(CreateText.this, R.color.blue));
                        pw.dismiss();
                    }
                });
                green.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        colour.setBackgroundColor(ContextCompat.getColor(CreateText.this, R.color.green));
                        input.setBackgroundColor(ContextCompat.getColor(CreateText.this, R.color.green));
                        pw.dismiss();
                    }
                });
                orange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        colour.setBackgroundColor(ContextCompat.getColor(CreateText.this, R.color.orange));
                        input.setBackgroundColor(ContextCompat.getColor(CreateText.this, R.color.orange));
                        pw.dismiss();
                    }
                });
                red.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        colour.setBackgroundColor(ContextCompat.getColor(CreateText.this, R.color.red));
                        input.setBackgroundColor(ContextCompat.getColor(CreateText.this, R.color.red));
                        pw.dismiss();
                    }
                });
                purple.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        colour.setBackgroundColor(ContextCompat.getColor(CreateText.this, R.color.purple));
                        input.setBackgroundColor(ContextCompat.getColor(CreateText.this, R.color.purple));
                        pw.dismiss();
                    }
                });
                white.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        colour.setBackgroundColor(ContextCompat.getColor(CreateText.this, R.color.white));
                        input.setBackgroundColor(ContextCompat.getColor(CreateText.this, R.color.white));
                        pw.dismiss();
                    }
                });
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable drawable = (ColorDrawable) colour.getBackground();
                if(drawable.getColor() == ContextCompat.getColor(CreateText.this,R.color.white))
                    mDatabase.child("text").child(title.getText().toString()).child("colour").setValue("white");
                else if(drawable.getColor() == ContextCompat.getColor(CreateText.this,R.color.blue))
                    mDatabase.child("text").child(title.getText().toString()).child("colour").setValue("blue");
                else if(drawable.getColor() == ContextCompat.getColor(CreateText.this,R.color.red))
                    mDatabase.child("text").child(title.getText().toString()).child("colour").setValue("red");
                else if(drawable.getColor() == ContextCompat.getColor(CreateText.this,R.color.green))
                    mDatabase.child("text").child(title.getText().toString()).child("colour").setValue("green");
                else if (drawable.getColor() == ContextCompat.getColor(CreateText.this,R.color.purple))
                    mDatabase.child("text").child(title.getText().toString()).child("colour").setValue("purple");
                else if(drawable.getColor() == ContextCompat.getColor(CreateText.this,R.color.orange))
                    mDatabase.child("text").child(title.getText().toString()).child("colour").setValue("orange");
                mDatabase.child("text").child(title.getText().toString()).child("body").setValue(body.getText().toString());
                finish();
            }
        });
    }
}