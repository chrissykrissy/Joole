package com.example.jooleproject.Entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

//import static com.example.jooleproject.Controller.ProductController.INCLUSION_FILTER;

@Entity
//@JsonFilter(INCLUSION_FILTER)
public class Product {

    @Id
    @GeneratedValue
    private Integer product_id;// productId

    @OneToMany (fetch = FetchType.LAZY,
            mappedBy = "product",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectProduct> projProduct;

    @OneToOne (cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "productType_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ProductType productType;

    @OneToOne (cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "technicalDetail_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
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
