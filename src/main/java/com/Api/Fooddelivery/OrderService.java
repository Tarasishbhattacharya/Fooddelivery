package com.Api.Fooddelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderrepository;
    public void addorder(Order order){
        orderrepository.addOrder(order);
    }

    public void addpartner(DeliveryPartner deliverypartner){
        orderrepository.addPartner(deliverypartner);
    }
    public void addOrderPartner(String orderid,String partnerid){
        orderrepository.addOrderPartnerPair(orderid,partnerid);
    }

    public Order getOrder(String orderid){
        Order order=orderrepository.getOrderById(orderid);
        return order;
    }

    public DeliveryPartner getPartner(String partnerid){
        DeliveryPartner deliverypartner=orderrepository.getPartnerById(partnerid);
        return deliverypartner;
    }
    public Integer getTotalOrder(String partnerid){
        int total=orderrepository.getTotal(partnerid);
        return total;
    }
    public List<String> getOrderByPartner(String partnerid){
        List<String> l=orderrepository.getpartnerOrder(partnerid);
        return l;
    }
    public List<String>  getOrders(){
        List<String> s=orderrepository.getAllOrders();
        return s;
    }
    public Integer getAllUnassignorder(){
        int count=orderrepository.allUnassignOrder();
        return count;
    }

    public void deletepartner(String partnerid){
        orderrepository.deletePartner(partnerid);
    }
    public void deleteorders(String orderid){
        orderrepository.deleteOrder(orderid);
    }

}
