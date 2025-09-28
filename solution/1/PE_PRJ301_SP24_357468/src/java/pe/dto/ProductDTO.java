/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.dto;

/**
 *
 * @author hd
 */
public class ProductDTO {
    private String productID;
    private String name;
    private String description;
    private float price;
    private String size;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(String productID, String name, String description, float price, String size, boolean status) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
        this.status = status;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}