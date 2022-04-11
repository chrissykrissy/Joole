package com.example.jooleproject.Controller;

import com.example.jooleproject.Entity.Product;
import com.example.jooleproject.Entity.ProductType;
import com.example.jooleproject.Entity.TechnicalDetail;
import com.example.jooleproject.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CustomerController")
public class ProductController {
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

    //create product
//    @PostMapping("/products/create")
//    public ResponseEntity<?> create(@RequestParam ("Manufacturer") String manufacturer,
//                          @RequestParam ("Series") String series,
//                          @RequestParam ("Model") String model){
//        Product created = productService.create(manufacturer, series, model);
//        if (created != null){
//            return new ResponseEntity<>(productService.list(), HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>("{\"Error\":\"Product could not be created!\"}", HttpStatus.BAD_REQUEST);
//        }
//    }

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
        ProductType createPT = productTypeService.create(useType, application, mountingLocation, accessories, year);
        TechnicalDetail createTD = techDetailService.create(airflow, maxPower, soundMax, diameter, height);
        created.setProductType(createPT);
        created.setTechnicalDetail(createTD);

        if (created != null && createPT != null && createTD != null){
            return new ResponseEntity<>(productService.list(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("{\"Error\":\"Product could not be created!\"}", HttpStatus.BAD_REQUEST);
        }
    }

    //find by Mechanical detail
    @GetMapping("/products/byMechanical")
    public ResponseEntity<?> findByManuSeriesModel(@RequestParam("Manufacture") String manufacturer,
                                                   @RequestParam("Series") String series,
                                                   @RequestParam("Model") String model){
        List<Product> list = productService.findByAll(manufacturer, series, model);

        if (list.isEmpty()){
            return new ResponseEntity<>("{\"Error\":\"Product could not be found!\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //find By ProductType
    @GetMapping("/products/byProductType")
    public ResponseEntity<?> findByProductType(@RequestParam ("UseType") String useType,
                                                   @RequestParam ("Application") String application,
                                                   @RequestParam ("Mounting Location") String mountingLocation,
                                                   @RequestParam ("Accessories") String accessories,
                                                   @RequestParam ("Year") int year){
        List<ProductType> list = productTypeService.findByAll(useType, application, mountingLocation, accessories, year);

        if (list.isEmpty()){
            return new ResponseEntity<>("{\"Error\":\"Product could not be found!\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //find By TechDetail
    @GetMapping("/products/byTechDetail")
    public ResponseEntity<?> findByTechDetail(@RequestParam ("Air Flow") int airflow,
                                                   @RequestParam ("Max Power") int maxPower,
                                                   @RequestParam ("Sound Max") int soundMax,
                                                   @RequestParam ("Diameter") int diameter,
                                                   @RequestParam ("Height") int height){
        List<TechnicalDetail> list = techDetailService.findByAll(airflow, maxPower, soundMax, diameter, height);

        if (list.isEmpty()){
            return new ResponseEntity<>("{\"Error\":\"Product could not be found!\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //update mechanical
    @PostMapping("products/updateByMechanical")

    //(String str, int num) num = 1, manu
//    num = 2 , series
    public ResponseEntity<?> findByManuSeriesModel(@RequestParam(name = "product_id") int productID,
                                                   @RequestParam(name ="Manufacture",required = false) String manufacturer,
                                                   @RequestParam(required = false, name = "Series") String series,
                                                   @RequestParam(required = false, name = "Model") String model){
        Product toUpdate = productService.updateAll(productID, manufacturer, series, model);
        if (toUpdate != null){
            return new ResponseEntity<>(toUpdate, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("{\"Error\":\"Product could not be updated!\"}", HttpStatus.BAD_REQUEST);
        }
    }

    //update type
    @PostMapping("products/updateByProductType")
    public ResponseEntity<?> findByManuSeriesModel(@RequestParam("ID") int ptID,
                                                   @RequestParam ("UseType") String useType,
                                                   @RequestParam ("Application") String application,
                                                   @RequestParam ("Mounting Location") String mountingLocation,
                                                   @RequestParam ("Accessories") String accessories,
                                                   @RequestParam ("Year") int year) {

        ProductType pt = productTypeService.updateAll(ptID, useType, application, mountingLocation, accessories, year);
        if (pt != null){
            return new ResponseEntity<>(pt.getProduct(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("{\"Error\":\"Product could not be updated!\"}", HttpStatus.BAD_REQUEST);
        }
    }

    //update Technical
    @PostMapping("products/updateByTechnical")
    public ResponseEntity<?> findByTechnicalDetail(@RequestParam("ID") int tdID,
                                                   @RequestParam ("Air Flow") int airflow,
                                                   @RequestParam ("Max Power") int maxPower,
                                                   @RequestParam ("Sound Max") int soundMax,
                                                   @RequestParam ("Diameter") int diameter,
                                                   @RequestParam ("Height") int height) {

        TechnicalDetail td = techDetailService.updateAll(tdID, airflow, maxPower, soundMax, diameter, height);
        if (td != null){
            return new ResponseEntity<>(td.getProduct(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("{\"Error\":\"Product could not be updated!\"}", HttpStatus.BAD_REQUEST);
        }
    }

    //delete by id?


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

    //Delete product by UseType
    @PostMapping("products/deleteByProductType")
    public ResponseEntity<?> deleteByUseType(@RequestParam("Use Type") String useType){
        productTypeService.deleteByUseType(useType);
        if (productTypeService.findByUseType(useType).isEmpty()){
            return new ResponseEntity<>("{Success: Product has been deleted}", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("{\"Error\":\"Product could not be deleted!\"}", HttpStatus.BAD_REQUEST);
        }
    }

    //delete by Tech
    @PostMapping("products/deleteByTechnicalDetail")
    public ResponseEntity<?> deleteByTechnicalDetail(@RequestParam("Height") int height){
        techDetailService.deleteByHeight(height);
        if (techDetailService.findByHeight(height).isEmpty()){
            return new ResponseEntity<>("{Success: Product has been deleted}", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("{\"Error\":\"Product could not be deleted!\"}", HttpStatus.BAD_REQUEST);
        }
    }
}
