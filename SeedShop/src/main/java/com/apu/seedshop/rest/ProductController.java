/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.Product;
import com.apu.seedshop.services.ProductMapper;
import com.apu.seedshop.services.ProductService;
import com.apu.seedshopapi.GenericReply;
import com.apu.seedshopapi.ProductListReply;
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
public class ProductController {
    private static final Logger logger =  LoggerFactory.getLogger(ProductController.class);
    @Autowired         
    ProductService productService;
    @Autowired
    ProductMapper productMapper;    
    
    @RequestMapping(path="/products/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductListReply getAllProducts(){
        ProductListReply reply = new ProductListReply();
        for(Product u: productService.getAllProducts()){
           reply.products.add(productMapper.fromInternal(u));    
        }
        return reply;
    }
    
    @RequestMapping(path="/products/bybarcode/{barcode}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductListReply getUserByBarcode(@PathVariable String barcode ){
        ProductListReply reply = new ProductListReply();
        reply.products.add(productMapper.fromInternal(productService.getProductByBarcode(barcode)));        
        return reply;
    }
    
    @RequestMapping(path="/products/del/{barcode}",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delProduct(@PathVariable String barcode ){
            GenericReply rep = new GenericReply();
        try{
            productService.delProduct(barcode);
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete product. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
}
