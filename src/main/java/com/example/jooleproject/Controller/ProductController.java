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

import java.util.List;

@RestController
@RequestMapping("/CustomerController")
public class ProductController {
    public static final String INCLUSION_FILTER = "manufacturer";
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


    //find by given para
    @GetMapping("/products/findBy")
    public ResponseEntity<?> findBy(@RequestParam(required = false, name = "Manufacturer") String manufacturer,
                                    @RequestParam (required = false, name = "Series") String series,
                                    @RequestParam (required = false, name = "Model") String model,
                                    @RequestParam (required = false, name = "UseType") String useType,
                                    @RequestParam (required = false, name = "Application") String application,
                                    @RequestParam (required = false, name = "Mounting Location") String mountingLocation,
                                    @RequestParam (required = false, name = "Accessories") String accessories,
                                    @RequestParam (required = false, name = "Year") int year,
                                    @RequestParam (required = false, name = "Air Flow") int airflow,
                                    @RequestParam (required = false, name = "Max Power") int maxPower,
                                    @RequestParam (required = false, name = "Sound Max") int soundMax,
                                    @RequestParam (required = false, name = "Diameter") int diameter,
                                    @RequestParam (required = false, name = "Height") int height) {



        List<Product> list1 = null;
        List<Product> list2 = null;
        List<Product> list3 = null;

        if (manufacturer != null){
            list1 = productService.findByManufacturer(manufacturer);
        }

        if (series != null){
            list2 = productService.findBySeries(series);
        }

        if (model != null){
            list3 = productService.findByModel(model);
        }

        list1.retainAll(list2);



//        List<Product> list = productService.list();
//        if (manufacturer != null){
//            list = productService.findByManufacturer(manufacturer);
//        }
//        if(series != null){
//            List<Product> seriesList = productService.findBySeries(series);
//            for(Product p : seriesList){
//                if(list.contains(p)){
//
//                }
//            }
//        }

        List<Product> full = productService.list();
        MappingJacksonValue tempMap = new MappingJacksonValue(full);
        PropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(manufacturer, series);
        FilterProvider filters = new SimpleFilterProvider().addFilter(INCLUSION_FILTER, filter);
        tempMap.setFilters(filters);
//        return tempMap;


//        try {
//            full = productService.list();
//            if (manufacturer != null) {
//                SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("product_id", "manufacturer");
//                FilterProvider filters = new SimpleFilterProvider().addFilter("mechanicalFilter", filter);
//                tempMap = new MappingJacksonValue(full);
//                tempMap.setFilters(filters);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>("{\"error\":\"" + "product could not be found!" + "\"}", HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(tempMap, HttpStatus.OK);
    }

    /*
    @GetMapping("/findAllProductsInProjectId/{projectId}")
    public ResponseEntity<?> findAllProductsInProjectId(@PathVariable int projectId) {
        List<ProjectProduct> temp;
        MappingJacksonValue tempMap;
        try {
            temp = projectProductService.findAllProducts(projectId);
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("projectProductId","product");
            FilterProvider filters = new SimpleFilterProvider().addFilter("ProjectProductFilter", filter);
            tempMap = new MappingJacksonValue(temp);
            tempMap.setFilters(filters);
        } catch(Exception e) {
            return new ResponseEntity<>("{\"error\":\""+e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tempMap, HttpStatus.CREATED);
    }
     */



    //find by Mechanical detail
    @GetMapping("/products/byMechanical")
    public ResponseEntity<?> findByManuSeriesModel(@RequestParam(required = false, name = "Manufacture") String manufacturer,
                                                   @RequestParam(required = false, name = "Series") String series,
                                                   @RequestParam(required = false, name = "Model") String model){
        List<Product> list = productService.findByAll(manufacturer, series, model);

        if (list.isEmpty()){
            return new ResponseEntity<>("{\"Error\":\"Product could not be found!\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //find By ProductType
    @GetMapping("/products/byProductType")
    public ResponseEntity<?> findByProductType(@RequestParam (required = false, name = "UseType") String useType,
                                                   @RequestParam (required = false, name = "Application") String application,
                                                   @RequestParam (required = false, name = "Mounting Location") String mountingLocation,
                                                   @RequestParam (required = false, name = "Accessories") String accessories,
                                                   @RequestParam (required = false, name = "Year") int year){
        List<ProductType> list = productTypeService.findByAll(useType, application, mountingLocation, accessories, year);

        if (list.isEmpty()){
            return new ResponseEntity<>("{\"Error\":\"Product could not be found!\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //find By TechDetail
    @GetMapping("/products/byTechDetail")
    public ResponseEntity<?> findByTechDetail(@RequestParam ("Air Flow") int airflow,
                                                   @RequestParam (required = false, name = "Max Power") int maxPower,
                                                   @RequestParam (required = false, name = "Sound Max") int soundMax,
                                                   @RequestParam (required = false, name = "Diameter") int diameter,
                                                   @RequestParam (required = false, name = "Height") int height){
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
                                                   @RequestParam (required = false, name = "UseType") String useType,
                                                   @RequestParam (required = false, name = "Application") String application,
                                                   @RequestParam (required = false, name = "Mounting Location") String mountingLocation,
                                                   @RequestParam (required = false, name = "Accessories") String accessories,
                                                   @RequestParam (required = false, name = "Year") int year) {

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
                                                   @RequestParam (required = false, name = "Air Flow") int airflow,
                                                   @RequestParam (required = false, name = "Max Power") int maxPower,
                                                   @RequestParam (required = false, name = "Sound Max") int soundMax,
                                                   @RequestParam (required = false, name = "Diameter") int diameter,
                                                   @RequestParam (required = false, name = "Height") int height) {

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
