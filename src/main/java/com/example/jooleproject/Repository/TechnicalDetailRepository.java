package com.example.jooleproject.Repository;

import com.example.jooleproject.Entity.TechnicalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface TechnicalDetailRepository extends JpaRepository<TechnicalDetail, Integer> {

//    TechnicalDetail findByTechnicalDetail_id(int id);

    List<TechnicalDetail> findByAirflowAfter(int airflow);
    List<TechnicalDetail> findByAirflowBetween(int first, int second);
    List<TechnicalDetail> findByAirflowBefore(int airflow);

    List<TechnicalDetail> findByMaxPowerAfter(int maxPower);
    List<TechnicalDetail> findByMaxPowerBetween(int first, int second);
    List<TechnicalDetail> findByMaxPowerBefore(int maxPower);
    
    List<TechnicalDetail> findBySoundMaxAfter(int soundMax);
    List<TechnicalDetail> findBySoundMaxBetween(int first, int second);
    List<TechnicalDetail> findBySoundMaxBefore(int soundMax);

    List<TechnicalDetail> findByDiameterAfter(int diameter);
    List<TechnicalDetail> findByDiameterBetween(int first, int second);
    List<TechnicalDetail> findByDiameterBefore(int diameter);

    List<TechnicalDetail> findByHeightAfter(int height);
    List<TechnicalDetail> findByHeightBetween(int first, int second);
    List<TechnicalDetail> findByHeightBefore(int height);

    List<TechnicalDetail> findByAirflowAndMaxPowerAndSoundMaxAndDiameterAndHeight(int airflow, int maxPower, int soundMax, int diameter, int height);

}
