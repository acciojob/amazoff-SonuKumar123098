package com.driver;

public class Code {
    public static int intTime(String deliveryTime){
        String[]time=deliveryTime.split(":");
        int val=0;
        val+=Integer.parseInt(time[0])*60;
        val+=Integer.parseInt(time[1]);
        return  val;
    }
    public static String stringTime(int deliveryTime){
        String HH=String.valueOf(deliveryTime/60);
        if(HH.length()<2) HH='0'+HH;
        String MM=String.valueOf(deliveryTime%60);
        if (MM.length()<2) MM='0'+MM;
        return HH+':'+MM;
    }
}
