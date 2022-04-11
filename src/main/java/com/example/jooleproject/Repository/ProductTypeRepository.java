package com.example.jooleproject.Repository;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>{

//    ProductType findByProductType_id(int id);

    List<ProductType> findByUseType(String useType);

    List<ProductType> findByApplication(String app);

    List<ProductType> findByMountingLocation(String loca);

    List<ProductType> findByAccessories(String acc);

    List<ProductType> findByYearAfter(int year);

    List<ProductType> findByYearBefore(int year);

    List<ProductType> findByYearBetween(int first, int second);

    List<ProductType> findByUseTypeAndApplicationAndMountingLocationAndAccessoriesAndYear(String useType, String app, String local, String acc, int year);

}
