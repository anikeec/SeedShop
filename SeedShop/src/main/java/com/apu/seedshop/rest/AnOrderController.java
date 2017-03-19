/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.AnOrder;
import com.apu.seedshop.services.AnOrderMapper;
import com.apu.seedshop.services.AnOrderService;
import com.apu.seedshopapi.GenericReply;
import com.apu.seedshopapi.AnOrderListReply;
import com.apu.seedshopapi.DeleteOrderListRequest;
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
    public AnOrderListReply getAllProducts(){
        AnOrderListReply reply = new AnOrderListReply();
        for(AnOrder ao: anOrderService.getAllAnOrders()){
           reply.orders.add(anOrderMapper.fromInternal(ao));    
        }
        return reply;
    }
    
    @RequestMapping(path="/orders/byorderid/{orderid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AnOrderListReply getAnOrdersByOrderId(@PathVariable Long orderid ){
        AnOrderListReply reply = new AnOrderListReply();
        for(AnOrder ao: anOrderService.getAnOrdersByOrderId(orderid)) {
            reply.orders.add(anOrderMapper.fromInternal(ao));  
        }
        return reply;
    }
    
    @RequestMapping(path="/orders/del/{id}",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delAnOrder(@PathVariable Long id ){
            GenericReply rep = new GenericReply();
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
    public GenericReply delListOrders(@RequestBody DeleteOrderListRequest req){
        GenericReply rep = new GenericReply();
        try{
            AnOrder order;
            for(Long id:req.ordersId) {
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
