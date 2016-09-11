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
    }
    
    public static void scenarioOne() throws IOException {
        Cart cart = new Cart(promotionalRule);
        
        cart.addCartItem(SIM.UNLIMITED_SMALL);
        cart.addCartItem(SIM.UNLIMITED_SMALL);
        cart.addCartItem(SIM.UNLIMITED_SMALL);
        
        cart.addCartItem(SIM.UNLIMITED_LARGE);
        
        cart.total();
        cart.listItems();
        System.out.println("====================================\nCart Total: " + cart.getCartTotal());
    }
    
    public static void scenarioTwo() throws IOException {
        Cart cart = new Cart(promotionalRule);
        
        cart.addCartItem(SIM.UNLIMITED_SMALL);
        cart.addCartItem(SIM.UNLIMITED_SMALL);
        
        cart.addCartItem(SIM.UNLIMITED_LARGE);
        cart.addCartItem(SIM.UNLIMITED_LARGE);
        cart.addCartItem(SIM.UNLIMITED_LARGE);
        cart.addCartItem(SIM.UNLIMITED_LARGE);
        
        cart.total();
        cart.listItems();
        System.out.println("====================================\nCart Total: " + cart.getCartTotal());
    }
    
    public static void scenarioThree() throws IOException {
        Cart cart = new Cart(promotionalRule);
        
        cart.addCartItem(SIM.UNLIMITED_SMALL);
        
        cart.addCartItem(SIM.UNLIMITED_MEDIUM);
        cart.addCartItem(SIM.UNLIMITED_MEDIUM);
        
        cart.total();
        cart.listItems();
        System.out.println("====================================\nCart Total: " + cart.getCartTotal());
    }
}
