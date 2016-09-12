/*
 * This code is a property of AmaySIM.
 * Any change to this code must have permission of the company.
 */
package com.amaysimshoppingcart.models.products;

import com.amaysimshoppingcart.utilities.PropertiesUtility;
import java.math.BigDecimal;
import java.util.Properties;
import java.io.IOException;

/**
 *
 * @author Jayvee
 * @since September 11, 2016
 */
public class Product {

    protected static Properties properties = null;
    protected static final String PRODUCT_PROPERTIES_FILE = "resources/products.properties";
    protected String PREFIX = "product";
    private String productCode = null;
    private String productName = null;
    private String promoCode = null;
    private BigDecimal price = null;
    private boolean freebie = false;

    public Product() throws IOException {
        if (properties == null) {
            properties = PropertiesUtility.loadProperties(PRODUCT_PROPERTIES_FILE);
        }
    }

    public Product(String prefix) throws IOException {
        PREFIX = prefix;
        
        if (properties == null) {
            properties = PropertiesUtility.loadProperties(PRODUCT_PROPERTIES_FILE);
        }

        this.productCode = properties.getProperty(PREFIX + ".code");
        this.productName = properties.getProperty(PREFIX + ".name");
        this.price = new BigDecimal(properties.getProperty(PREFIX + ".price"));
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the promoCode
     */
    public String getPromoCode() {
        return promoCode;
    }

    /**
     * @param promoCode the promoCode to set
     */
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = new BigDecimal(price);
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = new BigDecimal(price);
    }

    /**
     * @return the freebie
     */
    public boolean isFreebie() {
        return freebie;
    }

    /**
     * @param freebie the freebie to set
     */
    public void setFreebie(boolean freebie) {
        this.freebie = freebie;
    }
    
    public void printProductDetails() {
        System.out.println("\t====================================");
        System.out.println("\tFree: " + this.isFreebie());
        System.out.println("\tProduct Code: " + this.productCode);
        System.out.println("\tProduct Name: " + this.productName);
        System.out.println("\tPromo Code: " + this.promoCode);
        System.out.println("\tProduct Price: " + this.price);
    }
}
