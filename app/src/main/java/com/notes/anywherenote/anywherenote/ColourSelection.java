package com.notes.anywherenote.anywherenote;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

/**
 * Created by susan_000 on 16-Mar-18.
 */

public class ColourSelection extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_colour);

        /*For pop up window to select colour*/
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        getWindow().setLayout((int)(dm.widthPixels*0.6),(int)(dm.heightPixels*0.3));

        ImageView blue= (ImageView) findViewById(R.id.sel_blue);
        ImageView green= (ImageView) findViewById(R.id.sel_green);
    }


}
