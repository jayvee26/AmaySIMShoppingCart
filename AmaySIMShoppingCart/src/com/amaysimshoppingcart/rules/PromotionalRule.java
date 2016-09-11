/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amaysimshoppingcart.rules;

import com.amaysimshoppingcart.constants.SIM;
import com.amaysimshoppingcart.models.carts.Cart;
import com.amaysimshoppingcart.models.products.Product;
import com.amaysimshoppingcart.models.products.ProductFactory;
import java.math.BigDecimal;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Jayvee
 * @since September 11, 2016
 */
public class PromotionalRule extends PricingRule {

    @Override
    public void processCart(Cart cart) throws IOException {
        BigDecimal cartTotal = processRules(cart);

        cart.setCartTotal(cartTotal.setScale(2));
    }

    /**
     * Handles the computation of the cart's total price including the
     * promotional offers and discounts.
     *
     * @param cart the cart to process
     * @return the total amount of the cart
     * @throws IOException
     */
    private BigDecimal processRules(Cart cart) throws IOException {
        BigDecimal cartTotal = BigDecimal.ZERO;
        List<Product> products = cart.getCartItems();
        List<Product> freebies = new ArrayList<>();
        HashSet processedFlag = new HashSet();

        for (Product product : products) {
            String productName = product.getProductName();
            // Unlimited 1GB (Small)
            if (productName.equalsIgnoreCase(SIM.UNLIMITED_SMALL)) {
                if (!processedFlag.contains(productName)) {
                    cartTotal = cartTotal.add(unlimitedSmallRule(products));
                    processedFlag.add(productName);
                }
            } // Unlimited 2GB (Medium)
            else if (productName.equalsIgnoreCase(SIM.UNLIMITED_MEDIUM)) {
                if (!processedFlag.contains(productName)) {
                    cartTotal = cartTotal.add(unlimitedMediumRule(products, freebies));
                    processedFlag.add(productName);
                }
            } // Unlimited 5GB (Large)
            else if (productName.equalsIgnoreCase(SIM.UNLIMITED_LARGE)) {
                if (!processedFlag.contains(productName)) {
                    cartTotal = cartTotal.add(unlimitedLargeRule(products));
                    processedFlag.add(productName);
                }
            } // 1GB Data Pack & Any Undefined Product Above
            else {
                cartTotal = cartTotal.add(product.getPrice());
            }

        }

        // Adds all the freebies in the cart.
        products.addAll(freebies);

        // Apply all the valid discount promo codes
        cartTotal = processPromoCodes(products, cartTotal);

        return cartTotal;
    }

    /**
     * Counts all the product matching the product name.
     *
     * @param products the products to look at
     * @param productName the product name to look for
     * @return the count of freebies matching the product name
     */
    private static int count(List<Product> products, String productName) {
        int count = 0;

        count = products.stream().filter((product) -> (product.getProductName().equalsIgnoreCase(productName))).map((_item) -> 1).reduce(count, Integer::sum);

        return count;
    }

    /**
     * Counts all the freebies matching the product name.
     *
     * @param products the products to look at
     * @param productName the product name to look for
     * @return the count of freebies matching the product name
     */
    private static int countFreebie(List<Product> products, String productName) {
        int count = 0;

        count = products.stream().filter((product) -> (product.getProductName().equalsIgnoreCase(productName) && product.isFreebie())).map((_item) -> 1).reduce(count, Integer::sum);

        return count;
    }

    /**
     * Handles the pricing rules for the Unlimited 1GB (Small) SIM.
     *
     * @param products the cart items
     * @return the total amount of the Unlimited 1GB (Small) SIM
     */
    private static BigDecimal unlimitedSmallRule(List<Product> products) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        int count = 0;

        for (Product product : products) {
            // Ignore all free Unlimited 1GB (Small) SIM
            if (product.getProductName().equalsIgnoreCase(SIM.UNLIMITED_SMALL) && !product.isFreebie()) {
                count++;

                // Every third Unlimited 1GB (Small) SIM is free
                if (count == 3) {
                    count = 0;
                } else {
                    totalAmount = totalAmount.add(product.getPrice());
                }

            }
        }

        return totalAmount;
    }

    /**
     * NOT IN USE ANYMORE. Handles the pricing rules for the Unlimited 2GB
     * (Small) SIM. Adds 1GB Data Pack SIM freebie to the original cart every 1
     * Unlimited 2GB (Medium) SIM.
     *
     * @param products the cart items
     * @return the total amount of the Unlimited 2GB (Medium) SIM
     * @throws IOException
     */
    private static BigDecimal unlimitedMediumRule(List<Product> products) throws IOException {
        int oneGBFreebieCount = countFreebie(products, SIM.ONE_GB);
        BigDecimal totalAmount = BigDecimal.ZERO;
        int freebieCount = 0;

        Product freebie = ProductFactory.createProduct(SIM.ONE_GB);
        freebie.setFreebie(true);
        freebie.setPrice(0);

        // Count the freebies to be added and compute the total amount
        for (Product product : products) {
            // Ignore all free Unlimited 2GB (Medium) SIM
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

    /**
     * Handles the pricing rules for the Unlimited 2GB (Small) SIM. Adds 1GB
     * Data Pack SIM freebie to the freebie cart every 1 Unlimited 2GB (Medium)
     * SIM.
     *
     * @param products the cart items
     * @param freebies the container for the possible freebies
     * @return the total amount of the Unlimited 2GB (Medium) SIM
     * @throws IOException
     */
    private static BigDecimal unlimitedMediumRule(List<Product> products, List<Product> freebies) throws IOException {
        int oneGBFreebieCount = countFreebie(products, SIM.ONE_GB);
        BigDecimal totalAmount = BigDecimal.ZERO;
        int freebieCount = 0;

        Product freebie = ProductFactory.createProduct(SIM.ONE_GB);
        freebie.setFreebie(true);
        freebie.setPrice(0);

        // Count the freebies to be added and compute the total amount
        for (Product product : products) {
            // Ignore all free Unlimited 5GB (Large) SIM
            if (product.getProductName().equalsIgnoreCase(SIM.UNLIMITED_MEDIUM) && !product.isFreebie()) {
                // Count the freebie to by added later.
                freebieCount++;
                totalAmount = totalAmount.add(product.getPrice());
            }
        }

        // Adds the freebies to the cart.
        for (int x = 0; x < (freebieCount - oneGBFreebieCount); x++) {
            freebies.add(freebie);
        }

        return totalAmount;
    }

    /**
     * Handles the pricing rules for the Unlimited 5GB (Large) SIM.
     *
     * @param products the cart items
     * @return the total amount of the Unlimited 5GB (Large) SIM
     */
    private static BigDecimal unlimitedLargeRule(List<Product> products) {
        int unlimitedLargeCount = count(products, SIM.UNLIMITED_LARGE);
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal discountedPrice = new BigDecimal("39.90");

        for (Product product : products) {
            // Ignore all free Unlimited 5GB (Large) SIM
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

    /**
     * Applies the promo code and its corresponding discount on the total amount
     * of the cart.
     *
     * @param products the products to process
     * @param totalAmount the total amount of the cart
     * @return the total discounted amount
     */
    private static BigDecimal processPromoCodes(List<Product> products, BigDecimal totalAmount) {
        // Lock flag to avoid unwanted double discount.
        boolean iLoveAmaySIM = false;

        for (Product product : products) {
            if (!(product.getPromoCode() == null)) {
                // Ignore all free products
                if (!product.isFreebie()) {
                    // 10% discount on total amount of the cart using I<3AMAYSIM promo code.
                    if (product.getPromoCode().equalsIgnoreCase("I<3AMAYSIM") && !iLoveAmaySIM) {
                        totalAmount = totalAmount.multiply(new BigDecimal("0.9"));
                        iLoveAmaySIM = true;
                    }
                }
            }
        }

        return totalAmount;
    }
}
