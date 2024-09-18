package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Cart.CartItem.CartItem;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Model.wishlist.Wishlist;
import com.example.Ecommerce.Service.CartItemService;
import com.example.Ecommerce.Service.ProductsService;
import com.example.Ecommerce.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/cartItem")
@CrossOrigin(value="*")

public class  CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/getall")
    public List<CartItem> getCartItem() {
        return cartItemService.getCartItem();
    }
    @GetMapping("/getall/{id}")
    public Optional<CartItem> getCartItemBYId(@PathVariable(value = "id") Long cartItemId) {
        return cartItemService.getCartItemBYId(cartItemId);
    }
    @PostMapping("/add")
    public void registerCartItem(@RequestParam(name ="cartId") @PathVariable Long cartId,@RequestParam(name ="productsId") @PathVariable Long productsId,@RequestParam(name ="productsSkuId")@PathVariable Long productsSkuId, @RequestBody CartItem cartItem) {
        cartItemService.addNewCartItem(cartId,productsId,productsSkuId, cartItem);
    }

    @DeleteMapping(path = "{cartItemId}")
    public void deleteCartItem(@PathVariable("cartItemId") Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
    }

    @PutMapping(path = "{cartItemId}")
    public void updateCartItem(@RequestBody CartItem cartItem, @PathVariable("cartItemId") Long cartItemId) {
        cartItemService.updateCartItem(cartItemId, cartItem);
    }
}
