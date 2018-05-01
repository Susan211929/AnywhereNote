package com.notes.anywherenote.anywherenote;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by susan_000 on 30-Apr-18.
 */

public class ChecklistAdapter extends ArrayAdapter<Item>{

    private Context mContext;
    private ArrayList<Item> checklistString= new ArrayList<>();

    public ChecklistAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Item> objects) {
        super(context, resource, objects);

        mContext = context;
        this.checklistString = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

        Item currentItem = checklistString.get(position);

        TextView itemName = (TextView) listItem.findViewById(R.id.item);
        itemName.setText(currentItem.getValue());

        return listItem;
    }
}
