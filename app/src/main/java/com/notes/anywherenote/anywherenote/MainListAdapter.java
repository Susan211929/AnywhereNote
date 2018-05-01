package com.notes.anywherenote.anywherenote;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by susan_000 on 30-Apr-18.
 */

public class MainListAdapter extends ArrayAdapter<MainItem>{

    private Context mContext;
    private ArrayList<MainItem> mainItem= new ArrayList<>();

    public MainListAdapter(@NonNull Context context, @LayoutRes int resource,@NonNull ArrayList<MainItem> objects) {
        super(context, resource);
        mContext=context;
        mainItem=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.main_item_layout, parent, false);

        MainItem currentItem = mainItem.get(position);

        TextView itemName = (TextView) listItem.findViewById(R.id.title);
        itemName.setText(currentItem.getTitle());

        ImageView image= (ImageView) listItem.findViewById(R.id.image);
        if(currentItem.getType().equals("checklist"))
            image.setImageResource(R.drawable.check_mark);
        else
            image.setImageResource(R.drawable.note);

        listItem.setBackgroundColor(colorSelect(currentItem.getColour()));
        return listItem;
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
            case "teal":return R.color.teal;
        }
        return 0;
    }
}
