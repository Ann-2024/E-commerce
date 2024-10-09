package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Cart.CartItem.CartItem;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Model.Users.Users;
import com.example.Ecommerce.Model.wishlist.Wishlist;
import com.example.Ecommerce.Service.CartItemService;
import com.example.Ecommerce.Service.ProductsService;
import com.example.Ecommerce.Service.WishlistService;
import com.example.Ecommerce.config.JwtAuthFilter;
import com.example.Ecommerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/cartItem")
@CrossOrigin(value="*")

public class  CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    public Users getCurrentUser()
    {

        String userInfo=jwtAuthFilter.current_user;

        return usersRepository.findByEmail(userInfo).get();


    }
    @GetMapping("/getall")
    public List<CartItem> getCartItem() {


        return cartItemService.getCartItem();
    }

    @GetMapping("/getall/{id}")
    public Optional<CartItem> getCartItemBYId(@PathVariable(value = "id") Long cartItemId) {
        return cartItemService.getCartItemBYId(cartItemId);
    }

    @GetMapping("/getByUser")
    public List<CartItem> getCartItemBYUser(@RequestParam(value = "id") Long cartId) {
        return cartItemService.getCartItemByUser(cartId);
    }


    @PostMapping("/add")
    public void registerCartItem(@RequestParam(name ="cartId")  Long cartId,
                                 @RequestParam(name ="productsId") Long productsId,
                                 @RequestParam(name ="productsSkuId") Long productsSkuId,
                                 @RequestBody CartItem cartItems) {
        cartItemService.addNewCartItem(cartId,productsId,productsSkuId, cartItems);
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
