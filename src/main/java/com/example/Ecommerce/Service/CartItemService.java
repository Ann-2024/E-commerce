package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Addresses.Addresses;
import com.example.Ecommerce.Model.Cart.Cart;
import com.example.Ecommerce.Model.Cart.CartItem.CartItem;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import com.example.Ecommerce.Model.Users.Users;
import com.example.Ecommerce.Model.wishlist.Wishlist;
import com.example.Ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductsSkusRepository productsSkusRepository;

    public List<CartItem> getCartItem() {
        return cartItemRepository.findAll();
    }
    public Optional<CartItem> getCartItemBYId(Long cartItemId) {

        return cartItemRepository.findById(cartItemId);
    }

    public void addNewCartItem(Long cartId,Long productsId,Long productsSkuId, CartItem cartItem) {
        Cart cart = cartRepository.findById(cartId).get();
        Products products = productsRepository.findById(productsId).get();
        ProductsSkus productsSkus = productsSkusRepository.findById(productsSkuId).get();
        System.out.println("Wishlist service");

        cartItem.setCreatedAt(LocalDateTime.now());
        cartItem.setDeletedAt(LocalDateTime.now());
        cartItem.setCart(cart);
        cartItem.setProducts(products);
        cartItem.setProductsSkus(productsSkus);
        cartItemRepository.save(cartItem);

    }

    public void deleteCartItem(Long cartItemId) {
        boolean exists =cartItemRepository.existsById(cartItemId);
        if (!exists) {
            throw new IllegalStateException("Wishlist with id " + cartItemId + " does not exist");
        }
        cartItemRepository.deleteById(cartItemId);
    }

    public void updateCartItem(Long cartItemId, CartItem updatedCartItem) {
        CartItem existingCartItem= cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + cartItemId + " does not exist"));


        String quantity = updatedCartItem.getQuantity();

        existingCartItem.setQuantity(quantity);
        existingCartItem.setCreatedAt(LocalDateTime.now());
        existingCartItem.setDeletedAt(LocalDateTime.now());

        cartItemRepository.save(existingCartItem);
    }
}



