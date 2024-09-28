package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Cart.Cart;
import com.example.Ecommerce.Model.Order.OrderDetails;
import com.example.Ecommerce.Service.CartService;
import com.example.Ecommerce.Service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/orderDetails")
@CrossOrigin(value="*")

public class  OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping("/getall")
    public List<OrderDetails> getOrderDetails() {
        return orderDetailsService.getOrderDetails();
    }

    @GetMapping("/getall/{id}")
    public Optional<OrderDetails> getOrderDetailsBYId(@PathVariable(value = "id") Long orderDetailsId) {
        return orderDetailsService.getOrderDetailsBYId(orderDetailsId);
    }

    @PostMapping("/add")
    public void registerNewOrderDetails(@RequestParam(name ="userId")@PathVariable Long userId, @RequestBody OrderDetails orderDetails) {
        orderDetailsService.addNewOrderDetails(userId,orderDetails);
    }

    @DeleteMapping(path = "{orderDetailsId}")
    public void deleteOrderDetails(@PathVariable("orderDetailsId") Long orderDetailsId) {
        orderDetailsService.deleteOrderDetails(orderDetailsId);
    }

    @PutMapping(path = "{orderDetailsId}")
    public void updateOrderDetails(@RequestBody OrderDetails orderDetails, @PathVariable("orderDetailsId") Long orderDetailsId) {
        orderDetailsService.updateOrderDetails(orderDetailsId, orderDetails);
    }

}
