/*
 * 
 * 
 */
package com.apu.seedshop.services;
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

  
    public List<Product> getAllProducts(){
        return  productRepository.findAll();
    }

    public Product getProductByBarcode(String barcode) {
        Product p = productRepository.findOne(barcode);
        return p;
    }

    public void delProduct(String barcode){
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
        }
    }
}
