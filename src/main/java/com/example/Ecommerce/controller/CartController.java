package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Cart.Cart;
import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Model.wishlist.Wishlist;
import com.example.Ecommerce.Service.CartService;
import com.example.Ecommerce.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/cart")
@CrossOrigin(value="*")

public class  CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/getall")
    public List<Cart> getCart() {
        return cartService.getCart();
    }

    @GetMapping("/getall/{id}")
    public Optional<Cart> getCartBYId(@PathVariable(value = "id") Long cartId) {
        return cartService.getCartBYId(cartId);
    }

    @GetMapping("/getCartByUser")
    public Optional<Cart> getCartByUser(@RequestParam(value = "id") Long userId) {
        return cartService.getCartByUser(userId);
    }

    @PostMapping("/add")
    public String registerNewCart(@RequestParam(name ="userId") Long userId) {
     return    cartService.addNewCart(userId);
    }

    @DeleteMapping(path = "{cartId}")
    public void deleteCart(@PathVariable("cartId") Long cartId) {
        cartService.deleteCart(cartId);
    }

    @PutMapping(path = "{cartId}")
    public void updateCartTotal(@RequestBody Cart cart, @PathVariable("cartId") Long cartId) {
        cartService.updateCartTotal(cartId, cart);
    }
}