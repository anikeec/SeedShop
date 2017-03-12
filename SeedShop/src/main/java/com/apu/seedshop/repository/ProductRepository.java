/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.apu.seedshop.jpa.Product;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, String> {
    public List<Product> findByBarcode(@Param("barcode") String barcode);
    @Override
    public List<Product> findAll();

}
