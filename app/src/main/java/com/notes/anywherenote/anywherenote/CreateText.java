package com.notes.anywherenote.anywherenote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by susan_000 on 04-Mar-18.
 */

public class CreateText extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        ImageView colour = (ImageView) findViewById(R.id.select_colour);
        colour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateText.this,ColourSelection.class));
            }
        });
    }
}
