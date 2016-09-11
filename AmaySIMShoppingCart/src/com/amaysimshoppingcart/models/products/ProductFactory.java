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
 */
public class ProductFactory {

    /**
     * Creates an instance of the Product object using the values defined in the
     * products.properties file. Make sure to create a new entry here everytime
     * you add a new product on the property file
     *
     * @param productName the product name matching the prefix in the property
     * file.
     * @return Product containing the values defined in the property file
     * @throws IOException
     */
    public static Product createProduct(String productName) throws IOException {
        try {
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
                    throw new IOException("Can't locate matching product from the product property file.");
            }
        } catch (IOException ex) {
            throw new IOException("Can't locate matching product or property from the product property file.");
        }
    }
}
