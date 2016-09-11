/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amaysimshoppingcart.rules;

import com.amaysimshoppingcart.constants.SIM;
import java.math.BigDecimal;
import java.util.List;
import com.amaysimshoppingcart.models.carts.Cart;
import com.amaysimshoppingcart.models.products.Product;
import com.amaysimshoppingcart.models.products.ProductFactory;
import java.io.IOException;

/**
 *
 * @author Jayvee
 * @since September 11, 2016
 * @version 1.0
 */
public class PromotionalRule extends PricingRule {

    @Override
    public void processCart(Cart cart) {

        BigDecimal cartTotal = BigDecimal.ZERO;

        cartTotal = processRules(cart, cartTotal);

        cart.setCartTotal(cartTotal);
    }

    private BigDecimal processRules(Cart cart, BigDecimal cartTotal) {
        List<Product> products = cart.getCartItems();
        int unlimitedOneGBCount = count(cart.getCartItems(), SIM.UNLIMITED_SMALL);
        int unlimitedTwoGBCount = count(cart.getCartItems(), SIM.UNLIMITED_MEDIUM);
        int unlimitedFiveGBCount = count(cart.getCartItems(), SIM.UNLIMITED_LARGE);
        int oneGBDataPackCount = count(cart.getCartItems(), SIM.ONE_GB);
        int oneGBDataPackFrebbieCount = countFreebie(cart.getCartItems(), SIM.ONE_GB);

        // Unlimited Small (1GB)
        if (unlimitedOneGBCount > 0) {
            cartTotal = cartTotal.add(unlimitedSmallRule(products));
        }
        // Unlimited Medium (2GB)
        if (unlimitedTwoGBCount > 0) {
            try {
                cartTotal = cartTotal.add(unlimitedMediumRule(products, oneGBDataPackFrebbieCount));
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        // Unlimited Large (5GB)
        if (unlimitedFiveGBCount > 0) {
            cartTotal = cartTotal.add(unlimitedLargeRule(products, unlimitedFiveGBCount));
        }
        // 1GB Data Pack
        if (oneGBDataPackCount > 0) {
            cartTotal = cartTotal.add(defaultRule(products, SIM.ONE_GB));
        }

        cartTotal = processPromoCodes(products, cartTotal);

        return cartTotal;
    }

    private static int count(List<Product> products, String productName) {
        int count = 0;

        count = products.stream().filter((product) -> (product.getProductName().equalsIgnoreCase(productName))).map((_item) -> 1).reduce(count, Integer::sum);

        return count;
    }

    private static int countFreebie(List<Product> products, String productName) {
        int count = 0;

        count = products.stream().filter((product) -> (product.getProductName().equalsIgnoreCase(productName) && product.isFreebie())).map((_item) -> 1).reduce(count, Integer::sum);

        return count;
    }

    private static BigDecimal defaultRule(List<Product> products, String productName) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(productName) && !product.isFreebie()) {
                totalAmount = totalAmount.add(product.getPrice());
            }
        }

        return totalAmount;
    }

    private static BigDecimal unlimitedSmallRule(List<Product> products) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        int count = 0;

        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(SIM.UNLIMITED_SMALL) && !product.isFreebie()) {
                count++;

                // Every third Unlimited Small SIM is free
                if (count == 3) {
                    count = 0;
                } else {
                    totalAmount = totalAmount.add(product.getPrice());
                }

            }
        }

        return totalAmount;
    }

    private static BigDecimal unlimitedMediumRule(List<Product> products, int oneGBFreebieCount) throws IOException {
        BigDecimal totalAmount = BigDecimal.ZERO;
        int freebieCount = 0;

        Product freebie = ProductFactory.createProduct(SIM.ONE_GB);
        freebie.setFreebie(true);
        freebie.setPrice(0);

        // Count the freebies to be added and compute the total amount
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(SIM.UNLIMITED_MEDIUM) && !product.isFreebie()) {
                // Count the freebie to by added later.
                freebieCount++;
                totalAmount = totalAmount.add(product.getPrice());
            }
        }

        // Adds the freebies to the cart.
        for (int x = 0; x < (freebieCount - oneGBFreebieCount); x++) {
            products.add(freebie);
        }

        return totalAmount;
    }

    private static BigDecimal unlimitedLargeRule(List<Product> products, int unlimitedLargeCount) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal discountedPrice = new BigDecimal("39.90");

        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(SIM.UNLIMITED_LARGE) && !product.isFreebie()) {
                if (unlimitedLargeCount > 3) {
                    // Use discounted price instead of the original product price.
                    totalAmount = new BigDecimal(unlimitedLargeCount).multiply(discountedPrice);
                } else {
                    // use the original product price.
                    totalAmount = totalAmount.add(product.getPrice());
                }
            }
        }

        return totalAmount;
    }

    private static BigDecimal processPromoCodes(List<Product> products, BigDecimal totalAmount) {
        for (Product product : products) {
            if (!(product.getPromoCode() == null)) {
                if (!product.isFreebie()) {
                    // 10% discount on total amount of the cart using I<3AMAYSIM promo code.
                    if (product.getPromoCode().equalsIgnoreCase("I<3AMAYSIM")) {
                        totalAmount = totalAmount.multiply(new BigDecimal("0.9"));
                    }
                }
            }
        }

        return totalAmount;
    }
}
