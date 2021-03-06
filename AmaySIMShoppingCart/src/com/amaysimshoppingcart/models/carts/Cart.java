/*
 * This code is a property of AmaySIM.
 * Any change to this code must have permission of the company.
 */
package com.amaysimshoppingcart.models.carts;

import com.amaysimshoppingcart.models.products.Product;
import com.amaysimshoppingcart.models.products.ProductFactory;
import com.amaysimshoppingcart.rules.PricingRule;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.io.IOException;

/**
 *
 * @author Jayvee
 * @since September 11, 2016
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
     * @param productName the product name to be added in the cart
     * @param promoCode the promo code to be applied later to the product or
     * cart
     * @throws java.io.IOException
     */
    public void addCartItem(String productName, String promoCode) throws IOException {
        Product product = ProductFactory.createProduct(productName);
        product.setPromoCode(promoCode);
        cartItems.add(product);
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

    public void total() throws IOException {
        pricingRule.processCart(this);
    }

    public void listItems() {
        cartItems.stream().forEach((product) -> {
            product.printProductDetails();
        });
    }
}
