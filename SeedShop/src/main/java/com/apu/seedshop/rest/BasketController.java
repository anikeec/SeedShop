/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.AnOrder;
import com.apu.seedshop.jpa.Appuser;
import com.apu.seedshop.jpa.Invoice;
import com.apu.seedshop.jpa.Product;
import com.apu.seedshop.services.AnOrderMapper;
import com.apu.seedshop.services.AnOrderService;
import com.apu.seedshop.services.InvoiceMapper;
import com.apu.seedshop.services.InvoiceService;
import com.apu.seedshop.services.ProductService;
import com.apu.seedshop.services.AppuserMapper;
import com.apu.seedshop.services.AppuserService;
import com.apu.seedshop.services.BasketMapper;
import com.apu.seedshopapi.SeedBasketAddRequest;
import com.apu.seedshopapi.SeedAnOrderItem;
import com.apu.seedshopapi.SeedBasketItem;
import com.apu.seedshopapi.SeedBasketListReply;
import com.apu.seedshopapi.SeedBasketDeleteRequest;
import com.apu.seedshopapi.SeedDeleteForIdListRequest;
import com.apu.seedshopapi.SeedGenericReply;
import com.apu.seedshopapi.SeedProduct;
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
    InvoiceController invoiceController;
    
    @Autowired
    AppuserService userService;
    
    @Autowired
    AppuserMapper userMapper;
    
    @Autowired
    AnOrderService aoService;
    
    @Autowired
    AnOrderController aoController;
    
    @Autowired
    AnOrderMapper aoMapper;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    BasketMapper basketMapper;
    
    @RequestMapping(path="/basket/all/{sessId}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedBasketListReply getAllOrders(@PathVariable String sessId){
        SeedBasketListReply rep = new SeedBasketListReply();       
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
                //get products from AnOrder for current Invoice                
                List<AnOrder> orders = (List<AnOrder>)invoice.getAnOrderCollection();
                rep = basketMapper.fromInternal(orders);
            } 
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding to basket. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    
    @RequestMapping(path="/basket/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedBasketListReply addToBasket( @RequestBody SeedBasketAddRequest req){
        SeedBasketListReply rep = new SeedBasketListReply();
        //open request
        //check if session exist
        String sessionId = req.sessionId;        
        Appuser u = null;
        Invoice invoice = null;
        List<Appuser> users = userService.findUserBySessionId(sessionId);        
        
        try {
        
            if(users.isEmpty()) {   //if not exist, then create new appuser & new invoice, extract orderId                
                u = userMapper.newUser();
                u.setSessId(sessionId);
                userService.addUser(u);
            } else {                //if exist, then extract userId, then OrderId for this user                
                if(users.size() > 1) {
                    logger.error("Error add to basket. Many users with equal sessId");
                    throw new Exception("Error add to basket. Many users with equal sessId");
                }
                u = users.get(0);
                List<Invoice> invoices = (List<Invoice>)u.getInvoiceCollection();
                for(Invoice inv:invoices) {
                    if(inv.getStatusId().getStatusId() == 0) {
                        invoice = inv;
                        break;
                    }
                }           
            } 

            List<AnOrder> availableOrders = null;
            if(invoice == null) { 
                invoice = invoiceMapper.newInvoice();
                invoice.setUserId(u);
                invoiceService.addInvoice(invoice);
            } else {                //when I add available positions, then I have to update them                
                availableOrders = (List<AnOrder>)invoice.getAnOrderCollection();
            }           

                                    //add products to AnOrder for current OrderId 
            Long anOrderId = null;
            String barcode;
            int amount;
            SeedBasketItem item;
            AnOrder order;
            for(int i=0;i<req.products.size();i++) {
                barcode = req.products.get(i).barcode;
                amount = req.products.get(i).amount;
                Boolean available = false;
                if(availableOrders != null) {
                    for(AnOrder ord: availableOrders) {
                        if(ord.getBarcode().getBarcode().equals(barcode)) {
                            amount += ord.getAmount();
                            ord.setAmount(amount);////ord.getAmount()
                            available = true;
                            anOrderId = aoService.addAnOrder(ord).getId();
                            break;
                        }
                    }
                }
                if(available == false) {
                    order = aoMapper.newAnOrder();
                    anOrderId = order.getId();
                    order.setOrderId(invoice);
                    order.setBarcode(productService.getProductByBarcode(barcode));
                    order.setAmount(amount);
                    aoService.addAnOrder(order);
                }             

                item = new SeedBasketItem();
                item.product = new SeedProduct();
                item.orderId = "" + anOrderId;
                item.product.barcode = barcode;
                item.count = amount;
                rep.basketItems.add(item);
            }
        
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding to basket. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/basket/del/invoice",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delAllProducts(@RequestBody SeedBasketDeleteRequest req){             
        SeedGenericReply rep = new SeedGenericReply();
        try {  
            SeedDeleteForIdListRequest dilr = new SeedDeleteForIdListRequest();
                                            //check if session exist
            List<Long> delList = userService.findInvoiceIdBySessionId(req.sessionId);
            if((req.itemsId != null)&&(req.itemsId.isEmpty())) {    //delete all invoices for current sessionId        
                for(Long id:delList) {
                    if(invoiceService.getInvoiceByOrderId(id).getStatusId().getStatusId() == 0) {                        
                        dilr.itemsId.add(id);//I can groupdelete only for temp invoices
                    }
                }
            } 
            if((req.itemsId != null)&&(!req.itemsId.isEmpty())) {   //delete only invoices in the list of invoices                
                for(Long id:req.itemsId) {
                    if(delList.contains(id)) {
                        dilr.itemsId.add(id);
                    }
                }
            }
            return invoiceController.delListInvoices(dilr);
        }catch(Exception e){            
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error deleting basket. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/basket/del/order",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delAllProductsBySessId(@RequestBody SeedBasketDeleteRequest req){//ModelAndView
        SeedGenericReply rep = new SeedGenericReply();       
        try {    
            SeedDeleteForIdListRequest dolr = new SeedDeleteForIdListRequest();
            //check if user with current sessionId exist
            Appuser user = null;
            if(userService.findUserBySessionId(req.sessionId) != null) {
                user = userService.findUserBySessionId(req.sessionId).get(0);
            }
            //check can this user delete AnOrder with current ID
            if((req.itemsId != null)&&(!req.itemsId.isEmpty())) {                
                //find invoice with statusId = 0
                Invoice currentInvoice = null;
                List<Long> delList = userService.findInvoiceIdBySessionId(req.sessionId);
                for(Long id:delList) {
                    if(invoiceService.getInvoiceByOrderId(id).getStatusId().getStatusId() == 0) {
                        currentInvoice = invoiceService.getInvoiceByOrderId(id);
                        break;
                    }
                }
                //delete only orders in the currentInvoice
                if(currentInvoice != null) {
                    for(Long id:req.itemsId) {
                        if(currentInvoice.getAnOrderCollection().contains(aoService.getAnOrderById(id))) {
                            dolr.itemsId.add(id);
                        }
                    }
                }
            }     
            return aoController.delListOrders(dolr);
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error deleting from basket. Expetion: " + e.getMessage(),e);
        }
        //return new ModelAndView ("redirect:/invoices/del/1");//TODO - change to list
        return rep;
    }
    
}
