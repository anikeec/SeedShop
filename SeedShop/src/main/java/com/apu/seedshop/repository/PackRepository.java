/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.Pack;
import org.springframework.data.repository.query.Param;

public interface PackRepository extends CrudRepository<Pack, Integer> {
    public List<Pack> findByPackId(@Param("packId") Integer packId);
    @Override
    public List<Pack> findAll();

}
