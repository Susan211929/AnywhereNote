package com.notes.anywherenote.anywherenote;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView email;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        /*For changing email id in header of navigation bar*/
        View headerView = navigationview.getHeaderView(0);
        email = (TextView) headerView.findViewById(R.id.email);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
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
                final EditText emailValidate = (EditText) setting.findViewById(R.id.edt_eid);
                Button done = (Button) setting.findViewById(R.id.done);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email_input = emailValidate.getText().toString().trim();
                        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        if(email_input.equals(""))
                            Toast.makeText(getApplicationContext(), "Please provide email address", Toast.LENGTH_SHORT).show();
                        else if (email_input.matches(emailPattern)) {
                            email.setText(email_input);
                            closeDialog();
                        } else
                            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    }

                    void closeDialog() {
                        dialog.dismiss();
                    }
                });
                adb.setView(setting);
                dialog = adb.create();
                dialog.show();
                break;
        }
        return true;
    }
}