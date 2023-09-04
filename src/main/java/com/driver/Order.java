package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        int val=0;
        val+=(int)(deliveryTime.charAt(0)-'0')*10;
        val+=(int)(deliveryTime.charAt(1)-'0');
        val*=60;
        val+=(int)(deliveryTime.charAt(3)-'0')*10;
        val+=(int)(deliveryTime.charAt(4)-'0');
        this.deliveryTime=val;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
