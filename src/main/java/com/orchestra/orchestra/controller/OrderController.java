package com.orchestra.orchestra.controller;

import com.orchestra.orchestra.modals.Order;
import com.orchestra.orchestra.modals.Order_Singer;
import com.orchestra.orchestra.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/place/vocal", consumes = "application/json")
    public boolean placeOrder(Principal principal, @RequestBody String json) {
        return orderService.place(json, principal);
    }

    @GetMapping(path = "/myOrders", produces = "application/json")
    public List<Order> getMyOrders(Principal principal) {
        return orderService.getOrders(principal);
    }

    @GetMapping(path = "/myOrders/{orderId}/singer")
    public List<Order_Singer> getOrderSingers(Principal principal, @PathVariable long orderId) {
        return orderService.getOrderSingers(orderId, principal);
    }

    @GetMapping(path = "/delete/{orderId}")
    public boolean delete(Principal principal, @PathVariable long orderId) {
        return orderService.delete(orderId, principal);
    }
}
