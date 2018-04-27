package com.notes.anywherenote.anywherenote;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.data;
import static android.R.attr.key;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView email;
    AlertDialog dialog;
    DatabaseReference r1,r2;
    String uid,text_title,colour,bod0y,checklist_title;
    private EditText pass1,pass2;
    DatabaseReference mDatabase;
    ChildEventListener childEventListener1,childEventListener2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Retrieving titles from db*/
        /*For text*/
        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference("users").child(uid);
        r1 = mDatabase.child("text");
        childEventListener1 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                text_title=dataSnapshot.getKey();
                colour=(String)dataSnapshot.child("colour").getValue();
                print(text_title,colorSelect(colour),R.drawable.note);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError)
            {}
        };
        r1.addChildEventListener(childEventListener1);

        /*For Checklist*/
        r2 = mDatabase.child("checklist");
        childEventListener2 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                checklist_title=dataSnapshot.getKey();
                print(checklist_title,colorSelect("white"),R.drawable.check_mark);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError)
            {}
        };
        r2.addChildEventListener(childEventListener2);

        /*Font style for app name*/
        TextView tx = (TextView)findViewById(R.id.app_name);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/MAGNETOB.TTF");
        tx.setTypeface(custom_font);

        /*Creating a new note*/
        FloatingActionButton create = (FloatingActionButton) findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CreateOption.class));
            }
        });

        /*Inserting navigation bar*/
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//Setting overflow menu
        NavigationView navigationview = (NavigationView) findViewById(R.id.navigationview);
        navigationview.setNavigationItemSelectedListener(this);
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        /*For setting email id in header of navigation bar*/
        View headerView = navigationview.getHeaderView(0);
        email = (TextView) headerView.findViewById(R.id.email);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email.setText(user.getEmail());
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {    //For items selected in navigation bar
        switch (item.getItemId()) {
            case R.id.view:
                View view = getLayoutInflater().inflate(R.layout.view, (ViewGroup) findViewById(R.id.color_view));
                PopupWindow pw = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,true);
                pw.showAtLocation(view, Gravity.CENTER,0,0);
                ImageView all = (ImageView) view.findViewById(R.id.all);
                ImageView red = (ImageView) view.findViewById(R.id.red);
                ImageView blue = (ImageView) view.findViewById(R.id.blue);
                ImageView orange = (ImageView) view.findViewById(R.id.orange);
                ImageView white = (ImageView) view.findViewById(R.id.white);
                ImageView purple = (ImageView) view.findViewById(R.id.purple);
                ImageView green = (ImageView) view.findViewById(R.id.green);
                break;
            case R.id.setting:
                final AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                View setting = getLayoutInflater().inflate(R.layout.setting, null);
                pass1= (EditText) setting.findViewById(R.id.pass1);
                pass2= (EditText) setting.findViewById(R.id.pass2);
                Button done = (Button) setting.findViewById(R.id.done);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkPassword(pass1,pass2))
                        {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.updatePassword(pass1.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("success", "User password updated.");
                                            }
                                        }
                                    });
                            closeDialog();
                        }
                    }
                    void closeDialog() {
                        dialog.dismiss();
                    }
                });
                adb.setView(setting);
                dialog = adb.create();
                dialog.show();
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,Login.class));
                break;
        }
        return true;
    }
    private boolean checkPassword(TextView pass1, TextView pass2) {
        if(pass1.getText().toString().equals(""))
        {
            Toast.makeText(this, "Password field is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(pass2.getText().toString().equals(""))
        {
            Toast.makeText(this, "Password field is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (pass1.getText().toString().equals(pass2.getText().toString()))
            return true;
        Toast.makeText(this, "Password fields don't match", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void print(String s1, int s2, int s3) {
        try {
            TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
            View tableRow = LayoutInflater.from(this).inflate(R.layout.main_item_layout, null, false);
            TextView t1 = (TextView) tableRow.findViewById(R.id.t1);
            ImageView i1 = (ImageView) tableRow.findViewById(R.id.i1);
            t1.setText(s1);t1.setBackgroundResource(s2);
            i1.setImageResource(s3);
            i1.setBackgroundResource(s2);
            tableLayout.addView(tableRow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int colorSelect(String s)
    {
        switch(s)
        {
            case "blue":return R.color.blue;
            case "green":return R.color.green;
            case "orange":return R.color.orange;
            case "red":return R.color.red;
            case "white":return R.color.white;
            case "purple":return R.color.purple;
        }
        return 0;
    }

}