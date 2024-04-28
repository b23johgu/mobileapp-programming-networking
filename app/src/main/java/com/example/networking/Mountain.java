package com.example.networking;

import com.google.gson.annotations.SerializedName;

public class Mountain {
    //Member variables
        private String ID;
        private String name;
        private String location;
        @SerializedName("cost")
        private int feet;
        @SerializedName("size")
        private int meter;

    //Constructor(s)
    public Mountain(String inName, String inLocation, int inHeight) {
        name = inName;
        this.location = inLocation;
        this.meter = inHeight;
    }

    public Mountain(String inName){
        name=inName;
        location="";
        meter=-1;
    }

    //Member methods
    public String toString() {
        return name;
    }

    public String info(){
        String str=name;
        str+=" is located in ";
        str+=location;
        str+=" and has a height of ";
        str+=Integer.toString(meter);
        str+="m";
        return str;
    }
    public void setHeight(int newHeight){
        meter = newHeight;
    }

}
