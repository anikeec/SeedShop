/*
 * 
 * 
 */
package com.apu.seedshop.repository;

import java.util.List;
import com.apu.seedshop.jpa.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {
    public List<Invoice> findByOrderId(@Param("orderId") Integer id);
    @Override
    public List<Invoice> findAll();

}
