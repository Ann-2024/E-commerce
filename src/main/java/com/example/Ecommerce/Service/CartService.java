package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Cart.Cart;
import com.example.Ecommerce.Model.Cart.CartItem.CartItem;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Users.Users;
import com.example.Ecommerce.Model.wishlist.Wishlist;
import com.example.Ecommerce.repository.CartRepository;
import com.example.Ecommerce.repository.ProductsRepository;
import com.example.Ecommerce.repository.UsersRepository;
import com.example.Ecommerce.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UsersRepository usersRepository;

    public List<Cart> getCart() {
        return cartRepository.findAll();
    }
    public Optional<Cart> getCartBYId(Long cartId) {

        return cartRepository.findById(cartId);
    }

    public String addNewCart( Long userId ) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));

        Long id = users.getId();
        Cart cart1 = cartRepository.findByUsersId(id);

        if (cart1 != null) {
            return "user already added to cart page";
        } else {

            System.out.println("Wishlist service");

            Cart cart = new Cart();

            cart.setCreatedAt(LocalDateTime.now());
            cart.setDeletedAt(LocalDateTime.now());
            cart.setUsers(users);
            cartRepository.save(cart);
            return "successfully added";
        }
    }
    public void deleteCart(Long cartId) {
        boolean exists = cartRepository.existsById(cartId);
        if (!exists) {
            throw new IllegalStateException("Wishlist with id " + cartId + " does not exist");
        }
        cartRepository.deleteById(cartId);
    }

    public void updateCart(Long cartId, BigDecimal updatedCart) {
        Cart existingCart= cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + cartId + " does not exist"));

        BigDecimal total = updatedCart;

        existingCart.setTotal(total);
        existingCart.setCreatedAt(LocalDateTime.now());
        existingCart.setDeletedAt(LocalDateTime.now());

        cartRepository.save(existingCart);
    }

    public void updateCartTotal(Long cartId, Cart updatedCart) {
        Cart existingCart= cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + cartId + " does not exist"));

        BigDecimal total = updatedCart.getTotal();

        existingCart.setTotal(total);
        existingCart.setCreatedAt(LocalDateTime.now());
        existingCart.setDeletedAt(LocalDateTime.now());

        cartRepository.save(existingCart);
    }
}



