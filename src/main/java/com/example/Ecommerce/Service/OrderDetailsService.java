package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Order.OrderDetails;
import com.example.Ecommerce.Model.Order.OrderItem;
import com.example.Ecommerce.Model.Order.PaymentDetails.PaymentDetails;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import com.example.Ecommerce.Model.Users.Users;
import com.example.Ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;
    @Autowired
    private UsersRepository usersRepository;
    public List<OrderDetails> getOrderDetails() {
        return orderDetailsRepository.findAll();
    }
    public Optional<OrderDetails> getOrderDetailsBYId(Long orderDetailsId) {

        return orderDetailsRepository.findById(orderDetailsId);
    }


    public void addNewOrderDetails( Long usersId, OrderDetails orderDetails) {

        Users users = usersRepository.findById(usersId).get();
        System.out.println("Wishlist service");

        orderDetails.setCreatedAt(LocalDateTime.now());
        orderDetails.setDeletedAt(LocalDateTime.now());
        orderDetails.setUsers(users);



        orderDetailsRepository.save(orderDetails);

    }
    public void deleteOrderDetails(Long orderDetailsId) {
        boolean exists = orderDetailsRepository.existsById(orderDetailsId);
        if (!exists) {
            throw new IllegalStateException("Wishlist with id " + orderDetailsId + " does not exist");
        }
        orderDetailsRepository.deleteById(orderDetailsId);
    }

    public void updateOrderDetails(Long orderDetailsId, OrderDetails updatedOrderDetails) {
        OrderDetails existingOrderDetails= orderDetailsRepository.findById(orderDetailsId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + orderDetailsId + " does not exist"));

        String total= updatedOrderDetails.getTotal();

        existingOrderDetails.setTotal(total);
        existingOrderDetails.setCreatedAt(LocalDateTime.now());
        existingOrderDetails.setDeletedAt(LocalDateTime.now());

        orderDetailsRepository.save(existingOrderDetails);
    }
}


