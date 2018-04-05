package com.notes.anywherenote.anywherenote;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView email;
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
    public boolean onCreateOptionsMenu(Menu menu) {         //For overflow menu
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) { //For overflow menu
        switch (item.getItemId())
        {

        }
        return false;
    }*/

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                startActivityForResult(new Intent(MainActivity.this, Setting.class),1);
                break;
        }
            return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1)
            if (resultCode == RESULT_OK) {
                email.setText(data.getData().toString());
            }
    }
}