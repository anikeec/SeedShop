/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.AnOrder;
import com.apu.seedshop.jpa.Product;
import com.apu.seedshop.repository.ProductRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    private static final Logger logger =  LoggerFactory.getLogger(ProductService.class);   

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductMapper ProductMapper;

  
    public List<Product> getAllProducts(){
        return  productRepository.findAll();
    }

    public Product getProductByBarcode(String barcode) throws IllegalArgumentException {
        if(barcode == null)    throw new IllegalArgumentException("barcode = null");
        Product p = productRepository.findOne(barcode);
        return p;
    }

    public void delProduct(String barcode) throws IllegalArgumentException {
        if(barcode == null)    throw new IllegalArgumentException("barcode = null");
        Product p = productRepository.findOne(barcode);
        if(p!=null){
            logger.debug(String.format("Deleting product %s, %s, %s, %s with id %s",                                         
                                        p.getProductId(), 
                                        p.getManufactId(), 
                                        p.getPackingId(),
                                        p.getPrice(),
                                        p.getProductId()));
            //productRepository.delete(p);
            p.setUsed(false);
            productRepository.save(p);
        }
    }
    
    public Product addProduct(Product p) throws IllegalArgumentException {  
        if(p == null)    
            throw new IllegalArgumentException("ProductController."
                    + "addProduct(). Input Product item = null");
        logger.debug(String.format("Adding product with barcode %s", 
                        p.getBarcode()));
        p = productRepository.save(p);
        return p;
    }
    
    public void delProductFull(String barcode) throws IllegalArgumentException {
        if(barcode == null)    throw new IllegalArgumentException("barcode = null");
        Product p = productRepository.findOne(barcode);
        if(p!=null){
            logger.debug(String.format("Deleting product full with id %s",                                         
                                        p.getProductId()));
            productRepository.delete(barcode);
        }
    }
    
}
