package com.notes.anywherenote.anywherenote;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by susan_000 on 04-Mar-18.
 */

public class CreateChecklist extends Activity {

    List<Item> list = new ArrayList<>();
    private DatabaseReference mDatabase;
    private EditText input,title;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);



        list.add(new Item("hi",false));
        list.add(new Item("hello",true));
        list.add(new Item("bye",false));
        list.add(new Item("get lost",true));
        print("TITLE",list);





        ImageView add = (ImageView) findViewById(R.id.add);
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference("users").child(uid);
        ImageView done = (ImageView) findViewById(R.id.done);
        title = (EditText) findViewById(R.id.title);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(CreateChecklist.this);
                View add_chcklst = getLayoutInflater().inflate(R.layout.add_checklist,null);
                input = (EditText) add_chcklst.findViewById(R.id.txt_edt);
                Button add_btn = (Button) add_chcklst.findViewById(R.id.btn_add);

                adb.setView(add_chcklst);
                dialog =adb.create();
                dialog.show();

                add_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.add(new Item((input.getText().toString()),false));
                        dialog.dismiss();
                    }
                });
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("checklist").child(title.getText().toString()).setValue(list);
                finish();
            }
        });

    }
    public void print(String head, List items) {
        try {
            ScrollView sv = (ScrollView) findViewById(R.id.sv);
            View item = LayoutInflater.from(this).inflate(R.layout.list_item, null, false);
            TextView title = (TextView) findViewById(R.id.title);
            TextView t1 = (TextView) item.findViewById(R.id.item);
            CheckBox cb = (CheckBox) item.findViewById(R.id.cb);
            title.setText(head);
            Item current;
            for(Object temp:items) {
                current=(Item)temp;
                t1.setText(current.value);
                System.out.println(current.value);
                if(current.flag)
                    cb.setChecked(true);
                else
                    cb.setChecked(false);
                sv.addView(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}