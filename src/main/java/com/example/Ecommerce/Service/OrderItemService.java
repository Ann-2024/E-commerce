package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Cart.Cart;
import com.example.Ecommerce.Model.Order.OrderDetails;
import com.example.Ecommerce.Model.Order.OrderItem;
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
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductsSkusRepository productsSkusRepository;
    public List<OrderItem> getOrderItem() {
        return orderItemRepository.findAll();
    }
    public Optional<OrderItem> getOrderItemBYId(Long orderItemId) {

        return orderItemRepository.findById(orderItemId);
    }

    public void addNewOrderItem( Long orderDetailsId,Long productsId,Long productsSkusId, OrderItem orderItem) {
        OrderDetails orderDetails = orderDetailsRepository.findById(orderDetailsId).get();
        Products products = productsRepository.findById(productsId).get();
        ProductsSkus productsSkus = productsSkusRepository.findById(productsSkusId).get();
        System.out.println("Wishlist service");

        orderItem.setCreatedAt(LocalDateTime.now());
        orderItem.setDeletedAt(LocalDateTime.now());
        orderItem.setOrderDetails(orderDetails);
        orderItem.setProducts(products);
        orderItem.setProductsSkus(productsSkus);

        orderItemRepository.save(orderItem);

    }
    public void deleteOrderItem(Long orderItemId) {
        boolean exists = orderItemRepository.existsById(orderItemId);
        if (!exists) {
            throw new IllegalStateException("Wishlist with id " + orderItemId + " does not exist");
        }
        orderItemRepository.deleteById(orderItemId);
    }

    public void updateOrderItem(Long orderItemId, OrderItem updatedOrderItem) {
        OrderItem existingOrderItem= orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + orderItemId + " does not exist"));

        String quantity = updatedOrderItem.getQuantity();
        String status=updatedOrderItem.getStatus();

        existingOrderItem.setQuantity(quantity);
        existingOrderItem.setStatus(status);
        existingOrderItem.setCreatedAt(LocalDateTime.now());
        existingOrderItem.setDeletedAt(LocalDateTime.now());

        orderItemRepository.save(existingOrderItem);
    }
}

