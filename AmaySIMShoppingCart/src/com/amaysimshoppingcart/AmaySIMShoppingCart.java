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
        System.out.println("\n\n\n");
        
        // Additional Test Scenario
        scenarioFive();
        System.out.println("\n\n\n");
        
        // Turn this on to show error upon adding non existing product.
//        scenarioSix();
    }

    public static void scenarioOne() throws IOException {
        System.out.println("=========== Scenario 1 =============");
        Cart cart = new Cart(promotionalRule);

        cart.addCartItem(SIM.UNLIMITED_SMALL);
        cart.addCartItem(SIM.UNLIMITED_SMALL);
        cart.addCartItem(SIM.UNLIMITED_SMALL);

        cart.addCartItem(SIM.UNLIMITED_LARGE);

        processCart(cart);
    }

    public static void scenarioTwo() throws IOException {
        System.out.println("=========== Scenario 2 =============");
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
        System.out.println("=========== Scenario 3 =============");
        Cart cart = new Cart(promotionalRule);

        cart.addCartItem(SIM.UNLIMITED_SMALL);

        cart.addCartItem(SIM.UNLIMITED_MEDIUM);
        cart.addCartItem(SIM.UNLIMITED_MEDIUM);

        processCart(cart);
    }

    public static void scenarioFour() throws IOException {
        System.out.println("=========== Scenario 4 =============");
        Cart cart = new Cart(promotionalRule);

        cart.addCartItem(SIM.UNLIMITED_SMALL, "I<3AMAYSIM");

        cart.addCartItem(SIM.ONE_GB, "I<3AMAYSIM");

        processCart(cart);
    }

    public static void scenarioFive() throws IOException {
        System.out.println("=========== Scenario 5 =============");
        Cart cart = new Cart(promotionalRule);

        cart.addCartItem(SIM.UNLIMITED_SMALL);
        cart.addCartItem(SIM.UNLIMITED_SMALL);
        cart.addCartItem(SIM.UNLIMITED_SMALL, "I<3AMAYSIM");

        cart.addCartItem(SIM.UNLIMITED_LARGE);

        processCart(cart);
    }

    public static void scenarioSix() throws IOException {
        System.out.println("=========== Scenario 6 =============");
        Cart cart = new Cart(promotionalRule);

        cart.addCartItem(SIM.UNLIMITED_SMALL);
        cart.addCartItem(SIM.UNLIMITED_SMALL);
        cart.addCartItem(SIM.UNLIMITED_SMALL, "I<3AMAYSIM");
        
        cart.addCartItem("Non Existing Product");

        cart.addCartItem(SIM.UNLIMITED_LARGE);

        processCart(cart);
    }

    private static void processCart(Cart cart) throws IOException {
        System.out.println("====================================");
        System.out.println("Added Cart Items: ");
        cart.listItems();
        cart.total();
        System.out.println("====================================");
        System.out.println("Cart Items: ");
        cart.listItems();
        System.out.println("====================================");
        System.out.println("Cart Total: " + cart.getCartTotal());
    }
}
