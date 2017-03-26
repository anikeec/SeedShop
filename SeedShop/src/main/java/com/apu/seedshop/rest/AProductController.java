/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.AProduct;
import com.apu.seedshop.services.AProductMapper;
import com.apu.seedshop.services.AProductService;
import com.apu.seedshopapi.SeedAProductFull;
import com.apu.seedshopapi.SeedAProductFullListReply;
import com.apu.seedshopapi.SeedAProductFullReply;
import com.apu.seedshopapi.SeedGenericReply;
import com.apu.seedshopapi.SeedAProductListReply;
import com.apu.seedshopapi.SeedAProductReply;
import com.apu.seedshopapi.SeedDeleteForIdListRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController; 


@RestController
public class AProductController {
    private static final Logger logger =  LoggerFactory.getLogger(AProductController.class);
    @Autowired         
    AProductService aProductService;
    @Autowired
    AProductMapper aProductMapper;    
    
    @RequestMapping(path="/aproduct/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedAProductListReply getAllAProducts(){
        SeedAProductListReply rep = new SeedAProductListReply();
        try {
            for(AProduct ao: aProductService.getAllAProducts()){
               rep.aProducts.add(aProductMapper.fromInternal(ao));    
            }
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error query aProducts. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/aproduct/byid/{id}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedAProductReply getAProductByOrderId(@PathVariable Integer id ){
        SeedAProductReply rep = new SeedAProductReply();
        try {
            AProduct product = null;
            product = aProductService.getAProductById(id);
            rep.aProduct = aProductMapper.fromInternal(product);
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error query aProduct. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/aproductf/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedAProductFullListReply getAllAProductsFull(){
        SeedAProductFullListReply rep = new SeedAProductFullListReply();
        try {
            SeedAProductFull apf = null;
            AProduct product = null;
            for(AProduct ao: aProductService.getAllAProducts()){
                apf = new SeedAProductFull();
                product = ao;
                apf.aProduct = aProductMapper.fromInternal(ao);
                while(true){
                    if(product.getParentId() == null) break;
                    product = product.getParentId();
                    apf.parent.add(0,aProductMapper.fromInternal(product));  
                }
                rep.aProductsF.add(apf);    
            }
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error query aProducts. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/aproductf/byid/{id}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedAProductFullReply getAProductFullByOrderId(@PathVariable Integer id ){
        SeedAProductFullReply rep = new SeedAProductFullReply();
        try {
            AProduct product = null;
            product = aProductService.getAProductById(id);
            rep.aProductF.aProduct = aProductMapper.fromInternal(product);
            while(true){
                if(product.getParentId() == null) break;
                product = product.getParentId();
                rep.aProductF.parent.add(0,aProductMapper.fromInternal(product));  
            }
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error query aProduct. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
//    @RequestMapping(path="/aproduct/del/{id}",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public SeedGenericReply delAProduct(@PathVariable Long id ){
//            SeedGenericReply rep = new SeedGenericReply();
//        try{
//            anOrderService.delAProduct(id);
//        }catch(Exception e){
//            rep.retcode = -1;
//            rep.error_message = e.getMessage();
//            logger.error("Error delete order. Expetion: " + e.getMessage(),e);
//        }
//        return rep;       
//    }
    
}
