package com.Api.Fooddelivery;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Repository
public class OrderRepository {

    HashMap<String, Order>orderMap=new HashMap<>();
    HashMap<String,DeliveryPartner>deliveryPartnerMap=new HashMap<>();
    HashMap<String,List<String>>partnerOrderMap=new HashMap<>();

    List<String>assignOrders=new ArrayList<>();
    public void addOrder(Order order){
        String id=order.getId();
        orderMap.put(id,order);
    }
    public void addPartner(DeliveryPartner deliverypartner){
        String id=deliverypartner.getId();
        deliveryPartnerMap.put(id,deliverypartner);
    }
    public void addOrderPartnerPair(String orderid,String partnerid){
          if(!assignOrders.contains(orderid)){
              assignOrders.add(orderid);
          }

        if(partnerOrderMap.containsKey(partnerid)){
            partnerOrderMap.get(partnerid).add(orderid);
        }else{
            List<String>orders=new ArrayList<>();
            orders.add(orderid);
            partnerOrderMap.put(partnerid,orders);
        }
    }

    public Order getOrderById(String orderid){
        return orderMap.get(orderid);
    }

    public DeliveryPartner getPartnerById(String partnerid){
        return deliveryPartnerMap.get(partnerid);
    }
    public Integer getTotal(String partnerid){
        int total=0;
        if(partnerOrderMap.containsKey(partnerid)){
            total=partnerOrderMap.get(partnerid).size();
        }
        return total;
    }
    public List<String> getpartnerOrder(String partnerid){
        return partnerOrderMap.get(partnerid);
    }

    public List<String> getAllOrders(){
        List<String> ol=new ArrayList<>();
        for(String p:partnerOrderMap.keySet()){
            List<String> a=partnerOrderMap.get(p);
            for(String o:a){
                if(!ol.contains(o)){
                    ol.add(o);
                }
            }
        }
        for(String p:orderMap.keySet()){
            if(!ol.contains(p)){
                ol.add(p);
            }
        }
        return ol;
    }
    public Integer allUnassignOrder(){
        int size=orderMap.size();
        int count=assignOrders.size();
        return size-count;
    }
    public void deletePartner(String partnerid){
        if(partnerOrderMap.containsKey(partnerid)){
            List<String>order=partnerOrderMap.get(partnerid);
            for(String p:order){
                orderMap.remove(p);
                assignOrders.remove(p);
            }
        }
        partnerOrderMap.remove(partnerid);
    }
    public void deleteOrder(String orderid){
        if(orderMap.containsKey(orderid)){
            orderMap.remove(orderid);
        }
        for(String s:partnerOrderMap.keySet()){
            List<String> l=partnerOrderMap.get(s);
            for(int i=0;i<l.size();i++){
                if(l.get(i).equals(orderid)){
                    l.remove(l.get(i));
                }
            }
            partnerOrderMap.put(s,l);
        }
        for(int i=0;i<assignOrders.size();i++){
            if(assignOrders.get(i).equals(orderid)){
                assignOrders.remove(assignOrders.get(i));
            }
        }
    }



}
