/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.ProductLocation;
import org.springframework.data.repository.query.Param;

public interface ProductLocationRepository extends CrudRepository<ProductLocation, Integer> {
    public List<ProductLocation> findByLocationId(@Param("locationId") Integer locationId);
    @Override
    public List<ProductLocation> findAll();

}
