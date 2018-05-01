package com.notes.anywherenote.anywherenote;

/**
 * Created by susan_000 on 21-Apr-18.
 */

public class Item{
    private String value;
    private boolean flag;

    Item(){}

    Item(String value,boolean flag){
        this.value=value;
        this.flag=flag;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getValue() {
        return value;
    }

    public boolean isFlag() {
        return flag;
    }
}