/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.AnOrder;
import com.apu.seedshop.services.AnOrderMapper;
import com.apu.seedshop.services.AnOrderService;
import com.apu.seedshopapi.SeedGenericReply;
import com.apu.seedshopapi.SeedAnOrderListReply;
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
public class AnOrderController {
    private static final Logger logger =  LoggerFactory.getLogger(AnOrderController.class);
    @Autowired         
    AnOrderService anOrderService;
    @Autowired
    AnOrderMapper anOrderMapper;    
    
    @RequestMapping(path="/orders/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedAnOrderListReply getAllProducts(){
        SeedAnOrderListReply reply = new SeedAnOrderListReply();
        for(AnOrder ao: anOrderService.getAllAnOrders()){
           reply.orders.add(anOrderMapper.fromInternal(ao));    
        }
        return reply;
    }
    
    @RequestMapping(path="/orders/byorderid/{orderid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedAnOrderListReply getAnOrdersByOrderId(@PathVariable Long orderid ){
        SeedAnOrderListReply reply = new SeedAnOrderListReply();
        for(AnOrder ao: anOrderService.getAnOrdersByOrderId(orderid)) {
            reply.orders.add(anOrderMapper.fromInternal(ao));  
        }
        return reply;
    }
    
    @RequestMapping(path="/orders/del/{id}",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delAnOrder(@PathVariable Long id ){
            SeedGenericReply rep = new SeedGenericReply();
        try{
            anOrderService.delAnOrder(id);
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete order. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
    @RequestMapping(path="/orders/del/list",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delListOrders(@RequestBody SeedDeleteForIdListRequest req){
        SeedGenericReply rep = new SeedGenericReply();
        try{
            AnOrder order;
            for(Long id:req.itemsId) {
                order = anOrderService.getAnOrderById(id);
                order.getOrderId().getAnOrderCollection().remove(order); //maybe its hack???
                anOrderService.delAnOrder(id);
            }
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete anOrder. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
}
