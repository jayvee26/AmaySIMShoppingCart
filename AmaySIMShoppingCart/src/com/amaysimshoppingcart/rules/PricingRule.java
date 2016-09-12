/*
 * This code is a property of AmaySIM.
 * Any change to this code must have permission of the company.
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
