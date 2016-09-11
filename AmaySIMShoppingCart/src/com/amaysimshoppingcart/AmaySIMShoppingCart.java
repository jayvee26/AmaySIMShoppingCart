/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amaysimshoppingcart;

import java.io.IOException;
import com.amaysimshoppingcart.models.carts.Cart;
import com.amaysimshoppingcart.constants.SIM;
import com.amaysimshoppingcart.rules.PromotionalRule;

/**
 *
 * @author Jayvee
 * @since September 11, 2016
 * @version 1.0
 */
public class AmaySIMShoppingCart {

    public static PromotionalRule promotionalRule = new PromotionalRule();

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        scenarioOne();
        System.out.println("\n\n\n");
        scenarioTwo();
        System.out.println("\n\n\n");
        scenarioThree();
        System.out.println("\n\n\n");
        scenarioFour();
    }

    public static void scenarioOne() throws IOException {
        Cart cart = new Cart(promotionalRule);

        cart.addCartItem(SIM.UNLIMITED_SMALL);
        cart.addCartItem(SIM.UNLIMITED_SMALL);
        cart.addCartItem(SIM.UNLIMITED_SMALL);

        cart.addCartItem(SIM.UNLIMITED_LARGE);

        processCart(cart);
    }

    public static void scenarioTwo() throws IOException {
        Cart cart = new Cart(promotionalRule);

        cart.addCartItem(SIM.UNLIMITED_SMALL);
        cart.addCartItem(SIM.UNLIMITED_SMALL);

        cart.addCartItem(SIM.UNLIMITED_LARGE);
        cart.addCartItem(SIM.UNLIMITED_LARGE);
        cart.addCartItem(SIM.UNLIMITED_LARGE);
        cart.addCartItem(SIM.UNLIMITED_LARGE);

        processCart(cart);
    }

    public static void scenarioThree() throws IOException {
        Cart cart = new Cart(promotionalRule);

        cart.addCartItem(SIM.UNLIMITED_SMALL);

        cart.addCartItem(SIM.UNLIMITED_MEDIUM);
        cart.addCartItem(SIM.UNLIMITED_MEDIUM);

        processCart(cart);
    }

    public static void scenarioFour() throws IOException {
        Cart cart = new Cart(promotionalRule);

        cart.addCartItem(SIM.UNLIMITED_SMALL);

        cart.addCartItem(SIM.ONE_GB, "I<3AMAYSIM");

        processCart(cart);
    }

    private static void processCart(Cart cart) {
        System.out.println("====================================");
        System.out.println("Added Cart Items: ");
        cart.listItems();
        cart.total();
        System.out.println("====================================");
        System.out.println("Expected Cart Items: ");
        cart.listItems();
        System.out.println("====================================");
        System.out.println("Cart Total: " + cart.getCartTotal());
    }
}
