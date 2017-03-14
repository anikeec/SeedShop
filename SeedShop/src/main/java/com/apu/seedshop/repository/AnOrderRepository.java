/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.AnOrder;
import org.springframework.data.repository.query.Param;

public interface AnOrderRepository extends CrudRepository<AnOrder, Long> {
    public List<AnOrder> findById(@Param("id") Long id);
    @Override
    public List<AnOrder> findAll();
}
