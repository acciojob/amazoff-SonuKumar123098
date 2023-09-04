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
    List<String>orderList=new ArrayList<>();
    public void addOrder(Order order) {
       orders.put(order.getId(),order);
       orderList.add(order.getId());
    }

    public void addPartner(String partnerId) {
        DeliveryPartner deliveryPartner=new DeliveryPartner(partnerId);
        partners.put(partnerId,deliveryPartner);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        if(!partnerOrderPair.containsKey(partnerId)){
            List<String>al=new ArrayList<>();
            al.add(orderId);
            partnerOrderPair.put(partnerId,al);
        }else{
            List<String>al=partnerOrderPair.get(partnerId);
            al.add(orderId);
        }
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
        return orderList;
    }

    public Integer getCountOfUnassignedOrders() {
        int cnt=0;
        for(List<String>order:partnerOrderPair.values()){
            cnt+=order.size();
        }
        return orderList.size()-cnt;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        int timeval=Code.intTime(time);
        int left=0;
        for(String orderId:partnerOrderPair.get(partnerId)){
            if(orders.get(orderId).getDeliveryTime()>timeval) left++;
        }
        return left;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        int time=0;
        for(String orderId:partnerOrderPair.get(partnerId)){
            if(orders.get(orderId).getDeliveryTime()>time) time=orders.get(orderId).getDeliveryTime();
        }
        return Code.stringTime(time);
    }

    public void deletePartnerById(String partnerId) {
        partners.remove(partnerId);
        partnerOrderPair.remove(partnerId);
    }

    public void deleteOrderById(String orderId) {
        orders.remove(orderId);
        for(int i=0;i<orderList.size();i++){
            if(orderList.get(i).equals(orderId)){
                orderList.remove(i);
                break;
            }
        }
        for(List<String>order:partnerOrderPair.values()){
            if(order.contains(orderId)){
                for(int i=0;i<order.size();i++){
                    if(order.get(i).equals(orderId)){
                        order.remove(i);
                        break;
                    }
                }
                break;
            }
        }
    }
}
