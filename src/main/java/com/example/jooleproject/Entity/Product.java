package com.example.jooleproject.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer product_id;

    @OneToMany (fetch = FetchType.LAZY,
            mappedBy = "product",
<<<<<<< Updated upstream
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
=======
            cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
>>>>>>> Stashed changes
    private List<ProjectProduct> projProduct;

    @OneToOne (cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "productType_id")
    private ProductType productType;

    @OneToOne (cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "technicalDetail_id")
    private TechnicalDetail technicalDetail;

    private String manufacturer;

    private String series;

    private String model;

    public Product() {
    }

    public Product(String manufacturer, String series, String model) {
        this.manufacturer = manufacturer;
        this.series = series;
        this.model = model;
    }

    public List<ProjectProduct> getProjProduct() {
        return projProduct;
    }

    public void setProjProduct(List<ProjectProduct> projProduct) {
        this.projProduct = projProduct;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public TechnicalDetail getTechnicalDetail() {
        return technicalDetail;
    }

    public void setTechnicalDetail(TechnicalDetail technicalDetail) {
        this.technicalDetail = technicalDetail;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", productType=" + productType +
                ", technicalDetail=" + technicalDetail +
                ", manufacturer='" + manufacturer + '\'' +
                ", series='" + series + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
