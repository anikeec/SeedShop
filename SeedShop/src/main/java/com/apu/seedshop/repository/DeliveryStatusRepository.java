/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.DeliveryStatus;
import org.springframework.data.repository.query.Param;

public interface DeliveryStatusRepository extends CrudRepository<DeliveryStatus, Integer> {
    public List<DeliveryStatus> findByStatusId(@Param("statusId") Integer statusId);
    @Override
    public List<DeliveryStatus> findAll();

}
