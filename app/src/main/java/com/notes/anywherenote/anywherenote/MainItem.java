package com.notes.anywherenote.anywherenote;

/**
 * Created by susan_000 on 01-May-18.
 */

public class MainItem {
    private String type;
    private String title;
    private String colour;

    MainItem(){}

    MainItem(String type, String title, String colour){
        this.type=type;
        this.title=title;
        this.colour=colour;
    }

    public String getTitle() {
        return title;
    }

//    public void setTitle(String title) {
//        this.title = title;
//    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
