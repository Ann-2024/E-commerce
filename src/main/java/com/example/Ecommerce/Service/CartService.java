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

    public void addNewCart( Long userId, Cart cart) {
        Users users = usersRepository.findById(userId).get();
        System.out.println("Wishlist service");

        cart.setCreatedAt(LocalDateTime.now());
        cart.setDeletedAt(LocalDateTime.now());
        cart.setUsers(users);
        cartRepository.save(cart);

    }
    public void deleteCart(Long cartId) {
        boolean exists = cartRepository.existsById(cartId);
        if (!exists) {
            throw new IllegalStateException("Wishlist with id " + cartId + " does not exist");
        }
        cartRepository.deleteById(cartId);
    }

    public void updateCart(Long cartId, Cart updatedCart) {
        Cart existingCart= cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + cartId + " does not exist"));

        String total = updatedCart.getTotal();

        existingCart.setTotal(total);
        existingCart.setCreatedAt(LocalDateTime.now());
        existingCart.setDeletedAt(LocalDateTime.now());

        cartRepository.save(existingCart);
    }
}



