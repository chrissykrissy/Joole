package com.example.jooleproject.Controller;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.ProductType;
import com.example.jooleproject.Entity.TechnicalDetail;
import com.example.jooleproject.Service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/CustomerController")
public class ProductController {
//    public static final String INCLUSION_FILTER = "manufacturer";
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private TechDetailService techDetailService;

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    //Get all products from database
    @GetMapping("/products")
    public ResponseEntity<?> list(){
        List<Product> list = productService.list();
        if(list.isEmpty()){
            return new ResponseEntity<>("{\"Error\":\"Product not found!\"}", HttpStatus.BAD_REQUEST);
        }else{
           return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    //create product with all info
    @PostMapping("/products/create")
    public ResponseEntity<?> create(@RequestParam("Manufacturer") String manufacturer,
                                    @RequestParam ("Series") String series,
                                    @RequestParam ("Model") String model,
                                    @RequestParam ("UseType") String useType,
                                    @RequestParam ("Application") String application,
                                    @RequestParam ("Mounting Location") String mountingLocation,
                                    @RequestParam ("Accessories") String accessories,
                                    @RequestParam ("Year") int year,
                                    @RequestParam ("Air Flow") int airflow,
                                    @RequestParam ("Max Power") int maxPower,
                                    @RequestParam ("Sound Max") int soundMax,
                                    @RequestParam ("Diameter") int diameter,
                                    @RequestParam ("Height") int height){
        Product created = productService.create(manufacturer, series, model);
        ProductType createPT = productTypeService.create(created, useType, application, mountingLocation, accessories, year);
        TechnicalDetail createTD = techDetailService.create(created, airflow, maxPower, soundMax, diameter, height);
        productService.save(created, createPT, createTD);

        if (created != null && createPT != null && createTD != null){
            return new ResponseEntity<>(productService.list(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("{\"Error\":\"Product could not be created!\"}", HttpStatus.BAD_REQUEST);
        }
    }

    //find by Mechanical detail
    @GetMapping("/products/byMechanical")
    public ResponseEntity<?> findByManuSeriesModel(@RequestParam(name = "Manufacture") String manufacturer,
                                                   @RequestParam(required = false, name = "Series") String series,
                                                   @RequestParam(required = false, name = "Model") String model){
        List<Product> list = null;
        if (series == null && model == null){
            list = productService.findByManufacturer(manufacturer);
        }else{
            try {
                list = productService.findByAll(manufacturer, series, model);
            }catch (Exception e){
                return new ResponseEntity<>("{\"Error\":\"Product could not be found!\"}", HttpStatus.BAD_REQUEST);
            }
        }

        if (list.isEmpty()){
            return new ResponseEntity<>("{\"Error\":\"Product could not be found!\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //find By ProductType
    @GetMapping("/products/byProductType")
    public ResponseEntity<?> findByProductType(@RequestParam (name = "UseType") String useType,
                                                   @RequestParam (name = "Application") String application,
                                                   @RequestParam (name = "Mounting Location") String mountingLocation,
                                                   @RequestParam (name = "Accessories") String accessories,
                                                   @RequestParam (name = "Year") int year){

        List<ProductType> list = productTypeService.findByAll(useType, application, mountingLocation, accessories, year);
        List<Product> ret = new ArrayList<>();
        for (ProductType pt : list){
            ret.add(pt.getProduct());
        }

        if (list.isEmpty()){
            return new ResponseEntity<>("{\"Error\":\"Product could not be found!\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    //find By TechDetail
    @GetMapping("/products/byTechDetail")
    public ResponseEntity<?> findByTechDetail(//@RequestBody TechnicalDetail technicalDetail){
            @RequestParam (required = false, name = "Air Flow From") Integer airflowFrom,
              @RequestParam (required = false, name = "Air Flow End") Integer airFlowEnd,
              @RequestParam (required = false, name = "Max Power From") Integer powerFrom,
              @RequestParam (required = false, name = "Max Power End") Integer powerEnd,
              @RequestParam (required = false, name = "Sound Max From") Integer soundFrom,
              @RequestParam (required = false, name = "Sound Max End") Integer soundEnd,
              @RequestParam (required = false, name = "Diameter From") Integer diameterFrom,
              @RequestParam (required = false, name = "Diameter End") Integer diameterEnd,
              @RequestParam (required = false, name = "Height From") Integer heightFrom,
              @RequestParam (required = false, name = "Height End") Integer heightEnd){

        List<Product> list = null;
        if (airflowFrom != null && airFlowEnd != null && powerFrom != null && powerEnd != null && soundFrom != null && soundEnd != null && diameterFrom != null && diameterEnd != null && heightEnd!= null && heightFrom != null){
            list = techDetailService.findByRange(airflowFrom, airFlowEnd, powerFrom, powerEnd, soundFrom, soundEnd, diameterFrom, diameterEnd, heightFrom, heightEnd);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }

        List<List<TechnicalDetail>> nonNullList = new ArrayList<>();
        List<List<Product>> ret = new ArrayList<>();

        List<TechnicalDetail> airList;
        List<TechnicalDetail> powerList;
        List<TechnicalDetail> soundList;
        List<TechnicalDetail> diaList;
        List<TechnicalDetail> heightList;

        if (airflowFrom != null && airFlowEnd != null){
            airList = techDetailService.findByAirflowRange(airflowFrom, airFlowEnd);
//            System.out.println(airList);
            nonNullList.add(airList);
        }

        if (powerFrom != null && powerEnd != null){
            powerList = techDetailService.findByPowerRange(powerFrom, powerEnd);
            nonNullList.add(powerList);
        }

        if (soundFrom != null && soundEnd != null){
            soundList = techDetailService.findBySoundRange(soundFrom, soundEnd);
            nonNullList.add(soundList);
        }

        if (diameterFrom != null && diameterEnd != null){
            diaList = techDetailService.findByDiaRange(diameterFrom, diameterEnd);
            nonNullList.add(diaList);
        }

        if (heightFrom != null && heightEnd != null){
            heightList = techDetailService.findByHeightRange(heightFrom, heightEnd);
            nonNullList.add(heightList);
        }

        for (int i = 0; i < nonNullList.size()-1; i++){
            nonNullList.get(0).retainAll(nonNullList.get(i+1));
        }

        for (TechnicalDetail td : nonNullList.get(0)){
            ret.add(productService.findByTechnicalDetail(td));
        }



        if (nonNullList.isEmpty()){
            return new ResponseEntity<>("{\"Error\":\"Product could not be found!\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }


    //update mechanical
    @PostMapping("products/updateByMechanical")
    public ResponseEntity<?> updateByManuSeriesModel(@RequestParam(name = "product_id") int productID,
                                                   @RequestParam(name ="Manufacture",required = false) String manufacturer,
                                                   @RequestParam(required = false, name = "Series") String series,
                                                   @RequestParam(required = false, name = "Model") String model){
        try{
            Product toUpdate = productService.findByID(productID);
            if (manufacturer != null){
                toUpdate.setManufacturer(manufacturer);
            }
            if (series != null){
                toUpdate.setSeries(series);
            }
            if (model != null){
                toUpdate.setModel(model);
            }
            productService.save(toUpdate);
            return new ResponseEntity<>(toUpdate, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("{\"Error\":\"Product could not be updated!\"}", HttpStatus.BAD_REQUEST);
        }
    }


    //update type
    @PostMapping("products/updateByProductType")
    public ResponseEntity<?> updateByType(@RequestParam("ID") int ptID,
                                                   @RequestParam (required = false, name = "UseType") String useType,
                                                   @RequestParam (required = false, name = "Application") String application,
                                                   @RequestParam (required = false, name = "Mounting Location") String mountingLocation,
                                                   @RequestParam (required = false, name = "Accessories") String accessories,
                                                   @RequestParam (required = false, name = "Year") Integer year) {

        try{
            ProductType pt = productTypeService.findByID(ptID);
            if(useType != null){
                pt.setUseType(useType);
            }
            if(application != null){
                pt.setApplication(application);
            }
            if(mountingLocation != null){
                pt.setMountingLocation(mountingLocation);
            }
            if(accessories != null){
                pt.setAccessories(accessories);
            }
            if(year != null){
                pt.setYear(year);
            }
            Product p = pt.getProduct();
            p.setProductType(pt);
            productTypeService.save(pt);
            productService.save(p);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("{\"Error\":\"Product could not be updated!\"}", HttpStatus.BAD_REQUEST);
        }
    }

    //update Technical
    @PostMapping("products/updateByTechnical")
    public ResponseEntity<?> updateByTechnicalDetail(@RequestParam("ID") int tdID,
                                                   @RequestParam (required = false, name = "Air Flow") Integer airflow,
                                                   @RequestParam (required = false, name = "Max Power") Integer maxPower,
                                                   @RequestParam (required = false, name = "Sound Max") Integer soundMax,
                                                   @RequestParam (required = false, name = "Diameter") Integer diameter,
                                                   @RequestParam (required = false, name = "Height") Integer height) {

        try{
            TechnicalDetail td = techDetailService.findByID(tdID);
            if(airflow != null){
                td.setAirflow(airflow);
            }
            if(maxPower != null){
                td.setMaxPower(maxPower);
            }
            if(soundMax != null){
                td.setSoundMax(soundMax);
            }
            if(diameter != null){
                td.setDiameter(diameter);
            }
            if(height != null){
                td.setHeight(height);
            }
            Product p = td.getProduct();
            p.setTechnicalDetail(td);
            techDetailService.save(td);
            productService.save(p);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("{\"Error\":\"Product could not be updated!\"}", HttpStatus.BAD_REQUEST);
        }

    }

    //delete by id
    @PostMapping("products/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam("ID") Integer id){
        productService.deleteById(id);
        if (productService.findByID(id) == null){
            return new ResponseEntity<>("{Success: Product has been deleted}", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("{\"Error\":\"Product could not be deleted!\"}", HttpStatus.BAD_REQUEST);
        }
    }



    //Delete product by manu
    @PostMapping("products/deleteByMechanical")
    public ResponseEntity<?> deleteByMechanical(@RequestParam("Manufacturer") String manu){
        productService.deleteByManufacturer(manu);
        if (productService.findByManufacturer(manu).isEmpty()){
            return new ResponseEntity<>("{Success: Product has been deleted}", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("{\"Error\":\"Product could not be deleted!\"}", HttpStatus.BAD_REQUEST);
        }
    }
}
