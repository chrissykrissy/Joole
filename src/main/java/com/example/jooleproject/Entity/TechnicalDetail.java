package com.example.jooleproject.Entity;

import javax.persistence.*;

@Entity
public class TechnicalDetail {
    @Id
    @GeneratedValue
    private Integer technicalDetail_id;

    @OneToOne (mappedBy = "technicalDetail", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Product product;

    private int airflow;

    private int maxPower;

    private int soundMax;

    private int diameter;

    private int height;

    public TechnicalDetail() {
    }

    public TechnicalDetail(int airflow, int maxPower, int soundMax, int diameter, int height) {
        this.airflow = airflow;
        this.maxPower = maxPower;
        this.soundMax = soundMax;
        this.diameter = diameter;
        this.height = height;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAirflow() {
        return airflow;
    }

    public void setAirflow(int airflow) {
        this.airflow = airflow;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public int getSoundMax() {
        return soundMax;
    }

    public void setSoundMax(int soundMax) {
        this.soundMax = soundMax;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "TechnicalDetail{" +
                "technicalDetail_id=" + technicalDetail_id +
                ", airflow=" + airflow +
                ", maxPower=" + maxPower +
                ", soundMax=" + soundMax +
                ", diameter=" + diameter +
                ", height=" + height +
                '}';
    }
}
