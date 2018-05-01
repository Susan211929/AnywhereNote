package com.notes.anywherenote.anywherenote;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by susan_000 on 04-Mar-18.
 */

public class CreateChecklist extends Activity{

    ArrayList<Item> list = new ArrayList<>();
    private ChecklistAdapter checklistAdapter;
    private DatabaseReference mDatabase;
    private EditText input,title;
    private Dialog dialog;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        ImageView add = (ImageView) findViewById(R.id.add);
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference("users").child(uid);
        ImageView done = (ImageView) findViewById(R.id.done);
        title = (EditText) findViewById(R.id.title);
        lv= (ListView) findViewById(R.id.lv);
        checklistAdapter = new ChecklistAdapter(this, R.layout.list_item, list);
        lv.setAdapter(checklistAdapter);

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
                        try {
                            checklistAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView) ((LinearLayout) view).getChildAt(0);
                if(!list.get(position).isFlag()) {
                    v.setPaintFlags(v.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    list.get(position).setFlag(true);
                }
                else {
                    v.setPaintFlags(v.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    list.get(position).setFlag(false);
                }
            }
        });
    }
}