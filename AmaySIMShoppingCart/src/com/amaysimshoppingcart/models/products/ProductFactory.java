/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amaysimshoppingcart.models.products;

import java.io.IOException;
import com.amaysimshoppingcart.constants.SIM;

/**
 *
 * @author Jayvee
 * @since September 11, 2016
 * @version 1.0
 */
public class ProductFactory {

    public static Product createProduct(String productName) throws IOException {
        switch (productName) {
            case SIM.UNLIMITED_SMALL:
                return new Product("unlimited.small");
            case SIM.UNLIMITED_MEDIUM:
                return new Product("unlimited.medium");
            case SIM.UNLIMITED_LARGE:
                return new Product("unlimited.large");
            case SIM.ONE_GB:
                return new Product("one.gigabyte");
            default:
                break;
        }
        
        return null;
    }
}
