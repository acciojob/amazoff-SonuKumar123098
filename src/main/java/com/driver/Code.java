package com.driver;

public class Code {
    public static int intTime(String deliveryTime){
        int val=0;
        val+=(int)(deliveryTime.charAt(0)-'0')*10;
        val+=(int)(deliveryTime.charAt(1)-'0');
        val*=60;
        val+=(int)(deliveryTime.charAt(3)-'0')*10;
        val+=(int)(deliveryTime.charAt(4)-'0');
        return  val;
    }
    public static String stringTime(int deliveryTime){
        String hour="";
        int h=deliveryTime/60;
        int min=deliveryTime%60;
        String minute="";
        if(h>=10) hour+=h;
        else hour+=("0"+h);
        if(min>=10) minute+=min;
        else minute+=("0"+min);
        return hour+":"+minute;
    }
}
