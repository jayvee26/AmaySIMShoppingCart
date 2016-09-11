/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amaysimshoppingcart.rules;

import com.amaysimshoppingcart.models.carts.Cart;
import com.amaysimshoppingcart.models.products.Product;
import java.io.IOException;
import java.math.BigDecimal;


/**
 *
 * @author Jayvee
 * @since September 11, 2016
 * @version 1.0
 */
public class PricingRule {

    public void processCart(Cart cart) throws IOException {
        BigDecimal cartTotal = BigDecimal.ZERO;

        for (Product product : cart.getCartItems()) {
            cartTotal = cartTotal.add(product.getPrice());
        }

        cart.setCartTotal(cartTotal.setScale(2));
    }
}
