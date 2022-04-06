//package com.example.jooleproject.Entity;
//
//import javax.persistence.*;
//
//@Entity
//public class MechanicalDetail {
//
//    @Id
//    @GeneratedValue
//    private Integer mechanicalDetail_id;
//
//    @OneToOne (mappedBy = "mechanicalDetail", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    private Product product;
//
//    private String manufacturer;
//
//    private String series;
//
//    private String model;
//
//    //one to one pointing back to product
//
//    public MechanicalDetail() {
//    }
//
//    public MechanicalDetail(String manufacturer, String series, String model) {
//        this.manufacturer = manufacturer;
//        this.series = series;
//        this.model = model;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public String getManufacturer() {
//        return manufacturer;
//    }
//
//    public void setManufacturer(String manufacturer) {
//        this.manufacturer = manufacturer;
//    }
//
//    public String getSeries() {
//        return series;
//    }
//
//    public void setSeries(String series) {
//        this.series = series;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    @Override
//    public String toString() {
//        return "MechanicalDetail{" +
//                "mechanicalDetail_id=" + mechanicalDetail_id +
//                ", manufacturer='" + manufacturer + '\'' +
//                ", series='" + series + '\'' +
//                ", model='" + model + '\'' +
//                '}';
//    }
//}
