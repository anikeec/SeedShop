/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.AProduct;
import org.springframework.data.repository.query.Param;

public interface AProductRepository extends CrudRepository<AProduct, Integer> {
    public List<AProduct> findByProductId(@Param("productId") Integer productId);
    @Override
    public List<AProduct> findAll();

}
