/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.Manufacture;
import org.springframework.data.repository.query.Param;

public interface ManufactureRepository extends CrudRepository<Manufacture, Integer> {
    public List<Manufacture> findByManufactId(@Param("manufactId") Integer manufactureId);
    @Override
    public List<Manufacture> findAll();

}
