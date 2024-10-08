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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartService cartService;

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

    public List<CartItem> getCartItemByUser(Long cartId) {

        return cartItemRepository.findByCartId(cartId);
    }

    public void addNewCartItem(Long cartId,Long productsId,Long productsSkuId, CartItem cartItem) {



        Cart cart = cartRepository.findById(cartId).get();
        Products products = productsRepository.findById(productsId).get();
        ProductsSkus productsSkus = productsSkusRepository.findById(productsSkuId).get();
        System.out.println("Wishlist service");

        BigDecimal proPrice = products.getPrice();

        BigDecimal Amount = cart.getTotal();

        BigDecimal productQuantityPrice = proPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));

        System.out.println(productQuantityPrice);

        BigDecimal totalAmount = Amount.add(productQuantityPrice);

         cartService.updateCart( cartId,  totalAmount);


        System.out.println("total amount for this user is "+totalAmount);
        cartItem.setCreatedAt(LocalDateTime.now());
        cartItem.setDeletedAt(LocalDateTime.now());
        cartItem.setCart(cart);
        cartItem.setProducts(products);
        cartItem.setProductsSkus(productsSkus);
        cartItemRepository.save(cartItem);

    }

    public void deleteCartItem(Long cartItemId) {

        CartItem existingCartItem1= cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + cartItemId + " does not exist"));

        Long id = existingCartItem1.getCart().getId();

        Cart cart = cartRepository.findById(id).get();


        BigDecimal total = cart.getTotal();

        BigDecimal proMoney = existingCartItem1.getProducts().getPrice();

        BigDecimal productQuantityPrice = proMoney
                                           .multiply(BigDecimal.valueOf(existingCartItem1.getQuantity()));

        BigDecimal totalAmount = total.subtract(productQuantityPrice);

        cartService.updateCart( id,  totalAmount);


        cartItemRepository.deleteById(cartItemId);
    }

    public void updateCartItem(Long cartItemId, CartItem updatedCartItem) {
        CartItem existingCartItem= cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + cartItemId + " does not exist"));

        Cart cart = existingCartItem.getCart();
        Products products = existingCartItem.getProducts();
        ProductsSkus productsSkus = existingCartItem.getProductsSkus();

        int initialQuantity = existingCartItem.getQuantity();


        System.out.println("Wishlist service");

        BigDecimal proPrice = products.getPrice();

        BigDecimal initialCost = proPrice.multiply(BigDecimal.valueOf(initialQuantity));


        BigDecimal initialTotal=cart.getTotal();

        BigDecimal costToUpdate =initialTotal.subtract(initialCost);


        BigDecimal productQuantityPrice = proPrice.multiply(BigDecimal.valueOf(updatedCartItem.getQuantity()));

        System.out.println(productQuantityPrice);

        BigDecimal totalAmount = costToUpdate.add(productQuantityPrice);

        cartService.updateCart( cart.getId(),  totalAmount);


        int quantity = updatedCartItem.getQuantity();

        existingCartItem.setQuantity(quantity);
        existingCartItem.setCreatedAt(LocalDateTime.now());
        existingCartItem.setDeletedAt(LocalDateTime.now());

        cartItemRepository.save(existingCartItem);
    }
}



