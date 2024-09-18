package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Cart.CartItem.CartItem;
import com.example.Ecommerce.Model.Notification.Notification;
import com.example.Ecommerce.Service.CartItemService;
import com.example.Ecommerce.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/notification")
@CrossOrigin(value="*")
public class  NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getall")
    public List<Notification> getNotification() {
        return notificationService.getNotification();
    }
    @GetMapping("/getall/{id}")
    public Optional<Notification> getNotificationBYId(@PathVariable(value = "id") Long notificationId) {
        return notificationService.getNotificationBYId(notificationId);
    }
    @PostMapping("/add")
    public void registerNotification(@RequestParam(name ="sellerId") @PathVariable Long sellerId,@RequestParam(name ="orderId") @PathVariable Long orderId, @RequestBody Notification notification) {
        notificationService.addNewNotification(sellerId,orderId, notification);
    }

    @DeleteMapping(path = "{notificationId}")
    public void deleteNotification(@PathVariable("notificationId") Long notificationId) {
        notificationService.deleteNotification(notificationId);
    }

    @PutMapping(path = "{notificationId}")
    public void updateNotification(@RequestBody Notification notification, @PathVariable("notificationId") Long notificationId) {
        notificationService.updateNotification(notificationId, notification);
    }
}

