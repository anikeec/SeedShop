/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.DeliveryService;
import org.springframework.data.repository.query.Param;

public interface DeliveryServiceRepository extends CrudRepository<DeliveryService, Integer> {
    public List<DeliveryService> findByDeliveryId(@Param("deliveryId") Integer deliveryId);
    @Override
    public List<DeliveryService> findAll();

}
