package com.notes.anywherenote.anywherenote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

/**
 * Created by susan_000 on 04-Mar-18.
 */

public class CreateOption extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_option);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        getWindow().setLayout((int)(dm.widthPixels*0.8),(int)(dm.heightPixels*0.4)-160);

        TextView text = (TextView) findViewById(R.id.add_text);
        text.setOnClickListener(this);
        TextView checklist = (TextView) findViewById(R.id.add_checklist);
        checklist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.add_text:
                startActivity(new Intent(CreateOption.this,CreateText.class));
                finish();
                break;

            case R.id.add_checklist:
                startActivity(new Intent(CreateOption.this,CreateChecklist.class));
                finish();
                break;
        }
    }
}
