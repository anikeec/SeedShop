package com.apu.seedshop.services;

import com.apu.seedshop.jpa.AProduct;
import com.apu.seedshop.repository.AProductRepository;
import com.apu.seedshopapi.SeedAProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AProductMapper {
    private static final Logger logger =  LoggerFactory.getLogger(AProductMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    
    @Autowired
    AProductRepository pRepository;
    
    @Autowired
    AProductService pService;
    
/**
 * Maps internal JPA model to external REST model
 * @param inp internal AProduct model
 * @return external REST SeedAProduct model
 */
    public SeedAProduct fromInternal(AProduct inp) throws IllegalArgumentException {
        if(inp == null) 
            throw new IllegalArgumentException("AProductMapper. fromInternal. input = null");
        SeedAProduct result = new SeedAProduct();          
        result.name = inp.getName();
        result.productId = inp.getProductId();
        if(inp.getParentId() != null)
            result.parentId = inp.getParentId().getProductId();
        else       
            result.parentId = null;
        result.used = inp.getUsed().toString();
        return result;
    }
    
    /**
 * Creates new AProduct with good Id
 * @return newly created AProduct with required fields set
 */
    public AProduct newAProduct() {
        AProduct item = new AProduct();
        boolean idOK = false;
        int id = 0;
        while (!idOK) {
            id++;
            idOK = !pRepository.exists(id);
        }
        item.setProductId(id);
        item.setParentId(null);
        item.setName("");
        item.setUsed(true); 
        return item;
    }
    
/**
 * Maps external REST model to internal AProduct;
 * If user does not exists in DB then creates new. If user already exists
 * then fetches user from DB and sets all fields from external REST model
 * @param sp REST model
 * @return internal AProduct with all required fields set
 */
    public AProduct toInternal(SeedAProduct sp) throws IllegalArgumentException {
        AProduct aProduct = null;
        if(sp == null) 
            throw new IllegalArgumentException("AProductMapper. toInternal. input = null");
        
        if (sp.productId != null) {     //first, check if it exists
            aProduct = pRepository.findOne(sp.productId);            
        }
        if(aProduct == null){            //not found, create new
            logger.debug("Creating new aproduct");
            aProduct = newAProduct();
            if(sp.productId != null)    aProduct.setProductId(sp.productId);
        } else {
            logger.debug("Updating existing aproduct");
        }      
        
        aProduct.setName(sp.name);
        
        if(sp.parentId != null) {
            aProduct.setParentId(pService.getAProductById(sp.parentId));
        } else {
            aProduct.setParentId(null);
        }

        if(sp.used != null)
            aProduct.setUsed(sp.used.equals("true"));
        
        return aProduct;
    }

}
