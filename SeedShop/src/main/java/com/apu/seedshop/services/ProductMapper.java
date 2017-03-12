package com.apu.seedshop.services;

import com.apu.seedshopapi.SeedProduct;
import com.apu.seedshop.jpa.Product;
import com.apu.seedshop.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ProductMapper {
    private static final Logger logger =  LoggerFactory.getLogger(ProductMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    
    @Autowired
    ProductRepository productRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param p internal product model
 * @return external REST product model
 */
    public SeedProduct fromInternal(Product p) {
        SeedProduct sp = null;
        if (p != null) {
            sp = new SeedProduct();          
            sp.barcode = p.getBarcode();
            sp.name = p.getProductId().getName();
            sp.weight = "" + p.getPackingId().getWeight();
            sp.amount = "" + p.getPackingId().getAmount();            
            sp.manufact = p.getManufactId().getName();
            sp.pack = p.getPackingId().getPackId().getName();
            sp.price = "" + p.getPrice();       
        }
        return sp;
    }

}
