package com.apu.seedshop.services;

import com.apu.seedshopapi.SeedProduct;
import com.apu.seedshop.jpa.Product;
import com.apu.seedshop.repository.ProductRepository;
import java.math.BigDecimal;
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
    
    @Autowired
    AProductService aProductService;
    
    @Autowired
    ManufactureService manufactureService;
    
    @Autowired
    PackingService packingService;
    
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
            sp.aProductId = p.getProductId().getProductId();
            sp.manufactId = p.getManufactId().getManufactId();
            sp.packingId = p.getPackingId().getPackingId();
            sp.price = "" + p.getPrice();
            sp.used = "" + p.getUsed();
        }
        return sp;
    }
    
/**
 * Creates new Product with used=true
 * @return newly created Product with required fields set
 */
    public Product newProduct() {
        Product item = new Product();
        item.setBarcode("");
        item.setUsed(true);
        item.setPrice(new BigDecimal(0));
        item.setProductId(null);
        item.setManufactId(null);
        item.setPackingId(null);
        return item;
    }
    
/**
 * Maps external REST model to internal Product;
 * If user does not exists in DB then creates new. If user already exists
 * then fetches user from DB and sets all fields from external REST model
 * @param sp REST model
 * @return internal Product with all required fields set
 */
    public Product toInternal(SeedProduct sp) throws IllegalArgumentException {
        Product product = null;
        if(sp == null) 
            throw new IllegalArgumentException("ProductMapper. toInternal. input = null");
        
        if (sp.barcode != null) {     //first, check if it exists
            product = productRepository.findOne(sp.barcode);            
        }
        if(product == null){            //not found, create new
            logger.debug("Creating new product");
            product = newProduct();
            if(sp.barcode != null)    product.setBarcode(sp.barcode);
        } else {
            logger.debug("Updating existing product");
        }      
        
        if(sp.price != null) {
            product.setPrice(new BigDecimal(sp.price));
        } else {
            product.setPrice(null);
        }
        
        if(sp.manufactId != null) {
            product.setManufactId(manufactureService.getManufactureById(sp.manufactId));
        } else {
            product.setManufactId(null);
        }
        
        if(sp.packingId != null) {
            product.setPackingId(packingService.getPackingById(sp.packingId));
        } else {
            product.setPackingId(null);
        }
        
        if(sp.aProductId != null) {
            product.setProductId(aProductService.getAProductById(sp.aProductId));
        } else {
            product.setProductId(null);
        }

        if(sp.used != null)
            product.setUsed(sp.used.equals("true"));
        
        return product;
    }

}
