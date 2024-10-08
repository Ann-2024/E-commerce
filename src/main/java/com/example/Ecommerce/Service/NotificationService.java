package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Cart.Cart;
import com.example.Ecommerce.Model.Notification.Notification;
import com.example.Ecommerce.Model.Order.OrderItem;
import com.example.Ecommerce.Model.Seller.Seller;
import com.example.Ecommerce.Model.Users.Users;
import com.example.Ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SellerRepository sellerRepository;
@Autowired
private OrderItemRepository orderItemRepository;
    public List<Notification> getNotification() {
        return notificationRepository.findAll();
    }
    public Optional<Notification> getNotificationBYId(Long notificationId) {

        return notificationRepository.findById(notificationId);
    }

    public void addNewNotification( Long sellerId,Long orderId,Notification notification) {
        Seller seller = sellerRepository.findById(sellerId).get();
        OrderItem orderItem = orderItemRepository.findById(orderId).get();
        System.out.println("Wishlist service");

        notification.setCreatedAt(LocalDateTime.now());
        notification.setDeletedAt(LocalDateTime.now());
        notification.setSeller(seller);
        notification.setOrderItem(orderItem);
        notificationRepository.save(notification);

    }
    public void deleteNotification(Long notificationId) {
        boolean exists = notificationRepository.existsById(notificationId);
        if (!exists) {
            throw new IllegalStateException("Wishlist with id " + notificationId + " does not exist");
        }
        notificationRepository.deleteById(notificationId);
    }

    public void updateNotification(Long notificationId, Notification updatedNotification) {
        Notification existingNotification= notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + notificationId + " does not exist"));
        String currentStatus = updatedNotification.getCurrentStatus();
        String message = updatedNotification.getMessage();
        existingNotification.setCurrentStatus(currentStatus);
        existingNotification.setMessage(message);
        existingNotification.setCreatedAt(LocalDateTime.now());
        existingNotification.setDeletedAt(LocalDateTime.now());

        notificationRepository.save(existingNotification);
    }
}
