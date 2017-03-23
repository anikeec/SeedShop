/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.Availability;
import org.springframework.data.repository.query.Param;

public interface AvailabilityRepository extends CrudRepository<Availability, Integer> {
    public List<Availability> findByLocationId(@Param("locationId") Integer locationId);
    @Override
    public List<Availability> findAll();

}
