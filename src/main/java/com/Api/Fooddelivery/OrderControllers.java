package com.Api.Fooddelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderControllers {
    @Autowired
    OrderService orderservice;
    @PostMapping("/add_order")
    public ResponseEntity<String> addOrder(@RequestBody()Order order){
         orderservice.addorder(order);
         return new ResponseEntity<>("Order has been created", HttpStatus.CREATED);
    }
    @PostMapping("/add_partner")
    public ResponseEntity<String> addPartner(@RequestBody()DeliveryPartner deliverypartner){
        orderservice.addpartner(deliverypartner);
        return new ResponseEntity<>("  Delivery partner has been created", HttpStatus.CREATED);
    }

    @PutMapping("/add_order_partner")
    public ResponseEntity<String> addOrderPartnerPair(@RequestParam("order") String orderid,@RequestParam("partner") String partnerid){
         orderservice.addOrderPartner(orderid,partnerid);
        return new ResponseEntity<>("  Delivery partner and order pair has been created", HttpStatus.CREATED);
    }

    @GetMapping("/get_order/{orderid}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderid){
        Order order=orderservice.getOrder(orderid);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/get_partner/{partnerid}")
    public ResponseEntity<DeliveryPartner> getPartnerById(@PathVariable String partnerid){
        DeliveryPartner partner=orderservice.getPartner(partnerid);
        return new ResponseEntity<>(partner, HttpStatus.OK);
    }

    @GetMapping("get_count_order/{partnerid}")
    public ResponseEntity<Integer> addTotalOrder(@PathVariable String partnerid){
        int orderCount=0;
        orderCount=orderservice.getTotalOrder(partnerid);
        return new ResponseEntity<>(orderCount,HttpStatus.OK);
    }

    @GetMapping("/get_order/{partnerid}")
    public ResponseEntity<List<String>> getOrdersBypartner(@PathVariable String partnerid){
        List<String> orders=orderservice.getOrderByPartner(partnerid);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/all_orders")
    public ResponseEntity<List<String>> getAllOrder(){
        List<String>allOrder=orderservice.getOrders();
        return new ResponseEntity<>(allOrder,HttpStatus.OK);
    }

    @GetMapping("/orders_not_assigned_to_partner")
    public ResponseEntity<Integer> getUnassignedOrders(){
//        int unassign_orders=0;
        Integer unassignorders=orderservice.getAllUnassignorder();
        return new ResponseEntity<>(unassignorders,HttpStatus.OK);
    }

    @DeleteMapping("/delete_partner/{partnerid}")
    public ResponseEntity<String> deletePartners(@PathVariable String partnerid){

        orderservice.deletepartner(partnerid);
        return new ResponseEntity<>("partners are deleted",HttpStatus.OK);
    }

    @DeleteMapping("/delete_orders/{orderid}")
    public ResponseEntity<String> deleteOrders(@PathVariable String orderid){

        orderservice.deleteorders(orderid);
        return new ResponseEntity<>("orders are deleted",HttpStatus.OK);
    }


}
