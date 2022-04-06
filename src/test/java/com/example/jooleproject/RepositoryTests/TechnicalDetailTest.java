package com.example.jooleproject.RepositoryTests;

import com.example.jooleproject.Entity.ProductType;
import com.example.jooleproject.Entity.TechnicalDetail;
import com.example.jooleproject.Repository.TechnicalDetailRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TechnicalDetailTest {

    @Autowired
    TechnicalDetailRepository technicalDetailRepository;

    @Test
    public void saveTest(){
        TechnicalDetail techDetail = new TechnicalDetail(5000,65,35,60,300);
        TechnicalDetail result = technicalDetailRepository.save(techDetail);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByAirflowAfter(){
        List<TechnicalDetail> result = technicalDetailRepository.findByAirflowAfter(3000);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByAirflowBetween(){
        List<TechnicalDetail> result = technicalDetailRepository.findByAirflowBetween(3000, 6000);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByAirflowBefore(){
        List<TechnicalDetail> result = technicalDetailRepository.findByAirflowBefore(6000);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByMaxPowerAfter(){
        List<TechnicalDetail> result = technicalDetailRepository.findByMaxPowerAfter(60);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByMaxPowerBetween(){
        List<TechnicalDetail> result = technicalDetailRepository.findByMaxPowerBetween(60, 70);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByMaxPowerBefore(){
        List<TechnicalDetail> result = technicalDetailRepository.findByMaxPowerBefore(70);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findBySoundMaxAfter(){
        List<TechnicalDetail> result = technicalDetailRepository.findBySoundMaxAfter(30);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findBySoundMaxBetween(){
        List<TechnicalDetail> result = technicalDetailRepository.findBySoundMaxBetween(30, 40);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByDiameterBefore(){
        List<TechnicalDetail> result = technicalDetailRepository.findByDiameterBefore(40);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByDiameterAfter(){
        List<TechnicalDetail> result = technicalDetailRepository.findByDiameterAfter(30);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByDiameterBetween(){
        List<TechnicalDetail> result = technicalDetailRepository.findByDiameterBetween(30, 65);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findBySoundMaxBefore(){
        List<TechnicalDetail> result = technicalDetailRepository.findBySoundMaxBefore(65);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByHeightAfter(){
        List<TechnicalDetail> result = technicalDetailRepository.findByHeightAfter(30);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByHeightBetween(){
        List<TechnicalDetail> result = technicalDetailRepository.findByHeightBetween(30, 65);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByHeightBefore(){
        List<TechnicalDetail> result = technicalDetailRepository.findByHeightBefore(65);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

}
