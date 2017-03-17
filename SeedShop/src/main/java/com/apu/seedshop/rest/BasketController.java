/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.AnOrder;
import com.apu.seedshop.jpa.Appuser;
import com.apu.seedshop.jpa.Invoice;
import com.apu.seedshop.services.AnOrderMapper;
import com.apu.seedshop.services.AnOrderService;
import com.apu.seedshop.services.InvoiceMapper;
import com.apu.seedshop.services.InvoiceService;
import com.apu.seedshop.services.ProductService;
import com.apu.seedshop.services.AppuserMapper;
import com.apu.seedshop.services.AppuserService;
import com.apu.seedshopapi.AddBasketRequest;
import com.apu.seedshopapi.AnOrderItem;
import com.apu.seedshopapi.BasketListReply;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.servlet.ModelAndView;


@RestController
public class BasketController {
    private static final Logger logger =  LoggerFactory.getLogger(BasketController.class);
    
    @Autowired         
    InvoiceService invoiceService;   
    
    @Autowired
    InvoiceMapper invoiceMapper;
    
    @Autowired
    AppuserService userService;
    
    @Autowired
    AppuserMapper userMapper;
    
    @Autowired
    AnOrderService aoService;
    
    @Autowired
    AnOrderMapper aoMapper;
    
    @Autowired
    ProductService productService;
    
    @RequestMapping(path="/basket/all/{sessId}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BasketListReply getAllOrders(@PathVariable String sessId){
        BasketListReply rep = new BasketListReply();       
        try {  
            Invoice invoice = null;   
            //check if session exist
            List<Appuser> users = userService.findUserBySessionId(sessId);
            if(!users.isEmpty()) {
                //if exist, then extract invoices for this user
                if(users.size() > 1) {
                    String err = "Error add to basket. Two users with equal session_id";
                    logger.error(err);
                    throw new Exception(err);
                } 
                Appuser u = users.get(0);
                List<Invoice> invoices = (List<Invoice>)u.getInvoiceCollection();
                for(Invoice inv:invoices) {
                    if(inv.getStatusId().getStatusId() == 0) {
                        invoice = inv;
                        break;
                    }
                }
                if(invoice == null) {
                    String err = "There is no invoice with status_id=0.";
                    logger.error(err);
                    throw new Exception(err);
                }
                //get products from AnOrder for current OrderId 
                AnOrderItem item;                
                List<AnOrder> orders = (List<AnOrder>)invoice.getAnOrderCollection();
                for(AnOrder order:orders){
                    item = new AnOrderItem();
                    item.barcode = order.getBarcode().getBarcode();
                    item.amount = order.getAmount();
                    rep.orderItems.add(item);
                }
            } 
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding to basket. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    
    @RequestMapping(path="/basket/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BasketListReply addToBasket( @RequestBody AddBasketRequest req){
        BasketListReply rep = new BasketListReply();
        //open request
        //check if session exist
        String sessionId = req.sessionId;
        List<Appuser> users = userService.findUserBySessionId(sessionId);
        Appuser u = null;
        Invoice invoice = null;
        
        try {
        
            if(users.isEmpty()) {
                //if not exist, then create new appuser & new invoice, extract orderId
                u = userMapper.newUser();
                userService.addUser(u);

            } else {
                //if exist, then extract userId, then OrderId for this user
                if(users.size() > 1) {
                    logger.error("Error add to basket. To users with equal sessId");
                } else {
                    u = users.get(0);
                    List<Invoice> invoices = (List<Invoice>)u.getInvoiceCollection();
                    for(Invoice inv:invoices) {
                        if(inv.getStatusId().getStatusId() == 0) {
                            invoice = inv;
                            break;
                        }
                    }
                }            
            } 

            List<AnOrder> availableOrders = null;
            if(invoice == null) { 
                invoice = invoiceMapper.newInvoice();
                invoice.setUserId(u);
                invoiceService.addInvoice(invoice);
            } else {
                //when I add available positions, then I have to update them
                availableOrders = (List<AnOrder>)invoice.getAnOrderCollection();
            }           

            //add products to AnOrder for current OrderId 
            String barcode;
            int amount;
            AnOrderItem item;
            AnOrder order;
            for(int i=0;i<req.products.size();i++) {
                barcode = req.products.get(i).barcode;
                amount = req.products.get(i).amount;
                Boolean available = false;
                if(availableOrders != null) {
                    for(AnOrder ord: availableOrders) {
                        if(ord.getBarcode().getBarcode().equals(barcode)) {
                            amount += ord.getAmount();
                            ord.setAmount(amount);
                            available = true;
                            aoService.addAnOrder(ord);
                            break;
                        }
                    }
                }
                if(available == false) {
                   order = aoMapper.newAnOrder();
                    order.setOrderId(invoice);
                    order.setBarcode(productService.getProductByBarcode(barcode));
                    order.setAmount(amount);
                    aoService.addAnOrder(order);
                }             

                item = new AnOrderItem();
                item.barcode = barcode;
                item.amount = amount;
                rep.orderItems.add(item);
            }
        
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding to basket. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/basket/del/{sessId}",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView delAllProducts(@PathVariable String sessId){
        BasketListReply rep = new BasketListReply();       
        try {    
            //check if session exist
//            List<Long> delList = userService.findInvoiceBySessionId(sessId);
//            if(delList != null) {
//                invoiceService.delInvoices(delList);                
//            }
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error deleting from basket. Expetion: " + e.getMessage(),e);
        }
        return new ModelAndView ("redirect:/invoices/del/1");//TODO - change to list
    }
    
}
