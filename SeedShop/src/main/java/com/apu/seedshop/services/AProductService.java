/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.AProduct;
import com.apu.seedshop.repository.AProductRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AProductService {
    private static final Logger logger =  LoggerFactory.getLogger(AProductService.class);   

    @Autowired
    AProductRepository aProductRepository;
    @Autowired
    AProductMapper AProductMapper;

  
    public List<AProduct> getAllAProducts(){
        return  aProductRepository.findAll();
    }

    public AProduct getAProductById(Integer id) throws IllegalArgumentException {
        if(id == null)    throw new IllegalArgumentException("id = null");
        AProduct p = aProductRepository.findOne(id);
        return p;
    }

    public void delAProduct(Integer id) throws IllegalArgumentException {
        if(id == null)    throw new IllegalArgumentException("id = null");
        AProduct p = aProductRepository.findOne(id);
        if(p!=null){
            logger.debug(String.format("Deleting aproduct %s, %s, %s with id %s",                                         
                                        p.getName(), 
                                        p.getParentId(), 
                                        p.getUsed(),
                                        p.getProductId()));
            p.setUsed(false);
            aProductRepository.save(p);
        }
    }
    
    public AProduct addAProduct(AProduct p) throws IllegalArgumentException {  
        if(p == null)    
            throw new IllegalArgumentException("AProductService."
                    + "addAProduct(). Input AProduct item = null");
        logger.debug(String.format("Adding aproduct with id %s", p.getProductId()));
        p = aProductRepository.save(p);
        return p;
    }
    
    public void delAProductFull(Integer id) throws IllegalArgumentException {
        if(id == null)    throw new IllegalArgumentException("id = null");
        AProduct p = aProductRepository.findOne(id);
        if(p!=null){
            logger.debug(String.format("Deleting aproduct full with id %s",                                         
                                        p.getProductId()));
            aProductRepository.delete(id);
        }
    }
}
