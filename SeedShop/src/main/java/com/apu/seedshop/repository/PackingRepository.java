/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.Packing;
import org.springframework.data.repository.query.Param;

public interface PackingRepository extends CrudRepository<Packing, Integer> {
    public List<Packing> findByPackingId(@Param("packingId") Integer packingId);
    @Override
    public List<Packing> findAll();

}
