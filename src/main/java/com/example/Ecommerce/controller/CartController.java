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

    @PostMapping("/add")
    public void registerNewCart(@RequestParam(name ="userId")@PathVariable Long userId, @RequestBody Cart cart) {
        cartService.addNewCart(userId, cart);
    }

    @DeleteMapping(path = "{cartId}")
    public void deleteCart(@PathVariable("cartId") Long cartId) {
        cartService.deleteCart(cartId);
    }

    @PutMapping(path = "{cartId}")
    public void updateCart(@RequestBody Cart cart, @PathVariable("cartId") Long cartId) {
        cartService.updateCart(cartId, cart);
    }
}