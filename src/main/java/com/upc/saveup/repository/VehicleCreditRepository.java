package com.upc.saveup.repository;

import com.upc.saveup.model.VehicleCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleCreditRepository extends JpaRepository<VehicleCredit, Integer>{
    List<VehicleCredit> findByUserId(Integer userId);
    void deleteByUserId(int userId);

}
