package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Order.OrderItem;
import com.example.Ecommerce.Model.wishlist.Wishlist;
import com.example.Ecommerce.Service.OrderItemService;
import com.example.Ecommerce.Service.WishlistService;
import com.example.Ecommerce.repository.OrderItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping(path = "/api/orderItem")
@CrossOrigin(value="*")

public class  OrderItemController {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/getall")
    public List<OrderItem> getOrderItem() {
        return orderItemService.getOrderItem();
    }
    @GetMapping("/getall/{id}")
    public Optional<OrderItem> getOrderItemBYId(@PathVariable(value = "id") Long orderItemId) {
        return orderItemService.getOrderItemBYId(orderItemId);
    }
    @PostMapping("/add")
    public void registerNewOrderItem(@RequestParam(name ="orderDetailsId") @PathVariable Long orderDetailsId,@RequestParam(name ="productsId") @PathVariable Long productsId,@RequestParam(name ="productsSkusId") @PathVariable Long productsSkusId, @RequestBody OrderItem orderItem) {
        orderItemService.addNewOrderItem(orderDetailsId,productsId,productsSkusId, orderItem);
    }

    @DeleteMapping(path = "{orderItemId}")
    public void deleteOrderItem(@PathVariable("orderItemId") Long orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
    }
    @PutMapping("/updateStatus/{orderItemId}")
    public void updateOrderItemStatus(@PathVariable Long orderItemId, @RequestParam(name = "status") String status) {
        orderItemService.updateOrderItemStatus(orderItemId,status);

    }


    @PutMapping(path = "{orderItemId}")
    public void updateOrderItem(@RequestBody OrderItem orderItem, @PathVariable("orderItemId") Long orderItemId) {
        orderItemService.updateOrderItem(orderItemId, orderItem);
    }


}