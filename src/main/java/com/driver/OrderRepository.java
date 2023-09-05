package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository
public class OrderRepository {
    HashMap<String,Order> orders=new HashMap<>();
    HashMap<String,DeliveryPartner>partners=new HashMap<>();
    HashMap<String, List<String>>partnerOrderPair=new HashMap<>();
    HashMap<String,String>orderPartnerPair=new HashMap<>();
    public void addOrder(Order order) {
       orders.put(order.getId(),order);
    }

    public void addPartner(String partnerId) {
        DeliveryPartner deliveryPartner=new DeliveryPartner(partnerId);
        partners.put(partnerId,deliveryPartner);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        if(!orders.containsKey(orderId) || !partners.containsKey(partnerId)) return;
        if(orderPartnerPair.containsKey(orderId))return;
        orderPartnerPair.put(orderId,partnerId);
        if(!partnerOrderPair.containsKey(partnerId)){
            List<String>al=new ArrayList<>();
            al.add(orderId);
            partnerOrderPair.put(partnerId,al);
        }else{
            List<String>al=partnerOrderPair.get(partnerId);
            al.add(orderId);
        }
        DeliveryPartner part=partners.get(partnerId);
        part.setNumberOfOrders(part.getNumberOfOrders()+1);
    }

    public Order getOrderById(String orderId) {
        return orders.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return partners.get(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return partnerOrderPair.get(partnerId).size();
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return partnerOrderPair.get(partnerId);
    }

    public List<String> getAllOrders() {
        List<String>orderList=new ArrayList<>();
        for(String key:orders.keySet())orderList.add(key);
        return orderList;
    }

    public Integer getCountOfUnassignedOrders() {
        return orders.size()-orderPartnerPair.size();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(Integer time, String partnerId) {
        int left=0;
        if(!partnerOrderPair.containsKey(partnerId)) return left;
        for(String orderId:partnerOrderPair.get(partnerId)){
            if(orders.get(orderId).getDeliveryTime()>time) left++;
        }
        return left;
    }

    public Integer getLastDeliveryTimeByPartnerId(String partnerId) {
        int time=0;
        for(String orderId:partnerOrderPair.get(partnerId)){
            if(orders.get(orderId).getDeliveryTime()>time) time=orders.get(orderId).getDeliveryTime();
        }
        return time;
    }

    public void deletePartnerById(String partnerId) {
        if(partners.containsKey(partnerId)) partners.remove(partnerId);
        List<String>order=new ArrayList<>();
        if(partnerOrderPair.containsKey(partnerId)) {
            order=partnerOrderPair.remove(partnerId);
        }
        for(String ord:order){
            orderPartnerPair.remove(ord);
        }
    }

    public void deleteOrderById(String orderId) {
        if (orders.containsKey(orderId)){
            orders.remove(orderId);
            if(orderPartnerPair.containsKey(orderId)){
                String partnerId=orderPartnerPair.remove(orderId);
                List<String>al=partnerOrderPair.get(partnerId);
                for(int i=0;i<al.size();i++){
                    if(al.get(i).equals(orderId)){
                        al.remove(i);
                        break;
                    }
                }
            }
        }

    }


}
