package com.example.jooleproject.Entity;

import javax.persistence.*;

@Entity
public class ProductType {

    @Id
    @GeneratedValue
    private Integer productType_id;

    @OneToOne (mappedBy = "productType", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Product product;

    private String useType;

    private String application;

    private String mountingLocation;

    private String accessories;

    private int year;

    public ProductType() {
    }

    public ProductType(String useType, String application, String mountingLocation, String accessories, int year) {
        this.useType = useType;
        this.application = application;
        this.mountingLocation = mountingLocation;
        this.accessories = accessories;
        this.year = year;
    }

    public Integer getProductType_id() {
        return productType_id;
    }

    public void setProductType_id(Integer productType_id) {
        this.productType_id = productType_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getMountingLocation() {
        return mountingLocation;
    }

    public void setMountingLocation(String mountingLocation) {
        this.mountingLocation = mountingLocation;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "productType_id=" + productType_id +
                ", useType=" + useType +
                ", application=" + application +
                ", mountingLocation=" + mountingLocation +
                ", accessories=" + accessories +
                ", year=" + year +
                '}';
    }
}
