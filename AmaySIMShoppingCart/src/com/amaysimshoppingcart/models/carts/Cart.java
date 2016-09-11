/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amaysimshoppingcart.models.carts;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import com.amaysimshoppingcart.models.products.Product;
import com.amaysimshoppingcart.models.products.ProductFactory;
import com.amaysimshoppingcart.rules.PricingRule;
import java.io.IOException;

/**
 *
 * @author Jayvee
 * @since September 11, 2016
 * @version 1.0
 */
public class Cart {

    private PricingRule pricingRule;
    private List<Product> cartItems;
    private BigDecimal cartTotal;

    public Cart() {
        cartItems = new ArrayList<>();
        pricingRule = new PricingRule();
    }


    public Cart(PricingRule pricingRule) {
        cartItems = new ArrayList<>();
        this.pricingRule = pricingRule;
    }

    /**
     * @return the pricingRule
     */
    public PricingRule getPricingRule() {
        return pricingRule;
    }

    /**
     * @param pricingRule the pricingRule to set
     */
    public void setPricingRule(PricingRule pricingRule) {
        this.pricingRule = pricingRule;
    }

    /**
     * @return the cartItems
     */
    public List<Product> getCartItems() {
        return cartItems;
    }

    /**
     * @param cartProducts the cartItems to set
     */
    public void setCartItems(List<Product> cartProducts) {
        this.cartItems = cartProducts;
    }

    /**
     * @param product the product to be added in the cart
     */
    public void addCartItem(Product product) {
        cartItems.add(product);
    }

    /**
     * @param productName the product name to be added in the cart
     * @throws java.io.IOException
     */
    public void addCartItem(String productName) throws IOException {
        cartItems.add(ProductFactory.createProduct(productName));
    }

    /**
     * @return the cartTotal
     */
    public BigDecimal getCartTotal() {
        return cartTotal;
    }

    /**
     * @param cartTotal the cartTotal to set
     */
    public void setCartTotal(BigDecimal cartTotal) {
        this.cartTotal = cartTotal;
    }
    
    public void total() {
        pricingRule.processCart(this);
    }
    
    public void listItems() {
        cartItems.stream().forEach((product) -> {
            product.printProductDetails();
        });
    }
}
