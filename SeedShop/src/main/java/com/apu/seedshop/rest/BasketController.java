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
import com.apu.seedshop.services.UserMapper;
import com.apu.seedshop.services.UserService;
import com.apu.seedshopapi.AddBasketRequest;
import com.apu.seedshopapi.AnProductItem;
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


@RestController
public class BasketController {
    private static final Logger logger =  LoggerFactory.getLogger(BasketController.class);
    
    @Autowired         
    InvoiceService invoiceService;   
    
    @Autowired
    InvoiceMapper invoiceMapper;
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserMapper userMapper;
    
    @Autowired
    AnOrderService aoService;
    
    @Autowired
    AnOrderMapper aoMapper;
    
    @Autowired
    ProductService productService;
    
    
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
                    List<Invoice> invoices = invoiceService.getInvoiceByUserId(u.getUserId());
                    for(Invoice inv:invoices) {
                        if(inv.getStatusId().getStatusId() == 0) {
                            invoice = inv;
                            break;
                        }
                    }
                }            
            } 

            if(invoice == null) { 
                invoice = invoiceMapper.newInvoice();
                invoice.setUserId(u);
                invoiceService.addInvoice(invoice);
            }

            //add products to AnOrder for current OrderId 
            String barcode;
            int amount;
            AnProductItem item;
            AnOrder order;
            for(int i=0;i<req.products.size();i++) {
                order = aoMapper.newAnOrder();
                barcode = req.products.get(i).barcode;
                amount = req.products.get(i).amount;
                order.setOrderId(invoiceService.getInvoiceByOrderId(invoice.getOrderId()));
                order.setBarcode(productService.getProductByBarcode(barcode));
                order.setAmount(amount);
                aoService.addAnOrder(order);
                item = new AnProductItem();
                item.barcode = barcode;
                item.amount = order.getAmount();
                rep.products.add(item);
            }
        
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding to basket. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
}
