/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.Appuser;
import com.apu.seedshop.jpa.Invoice;
import com.apu.seedshop.services.InvoiceMapper;
import com.apu.seedshop.services.InvoiceService;
import com.apu.seedshop.services.AppuserService;
import com.apu.seedshop.services.DeliveryStatusService;
import com.apu.seedshopapi.SeedInvoiceAddRequest;
import com.apu.seedshopapi.SeedDeleteForIdListRequest;
import com.apu.seedshopapi.SeedGenericReply;
import com.apu.seedshopapi.SeedInvoiceListReply;
import java.util.List;
import java.util.logging.Level;
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
public class InvoiceController {
    private static final Logger logger =  LoggerFactory.getLogger(InvoiceController.class);
    @Autowired         
    InvoiceService invoiceService;
    @Autowired
    InvoiceMapper invoiceMapper;
    @Autowired    
    AppuserService userService;
    @Autowired
    DeliveryStatusService dStatService;
    
    @RequestMapping(path="/invoices/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedInvoiceListReply getAllInvoices(){
        SeedInvoiceListReply reply = new SeedInvoiceListReply();
        for(Invoice u: invoiceService.getAllInvoices()){
           reply.invoices.add(invoiceMapper.fromInternal(u));    
        }
        return reply;
    }
    
    @RequestMapping(path="/invoices/byid/{orderid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedInvoiceListReply getInvoiceByOrderId(@PathVariable Long orderid){
        SeedInvoiceListReply reply = new SeedInvoiceListReply();
        reply.invoices.add(invoiceMapper.fromInternal(invoiceService.getInvoiceByOrderId(orderid)));        
        return reply;
    }
    
    @RequestMapping(path="/invoices/bysessid/{sessId}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedInvoiceListReply getInvoiceBySessionId(@PathVariable String sessId){
        SeedInvoiceListReply reply = new SeedInvoiceListReply();
        List<Long> list = null;
        try {
            list = userService.findInvoiceIdBySessionId(sessId);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(list != null) {
            for(Long orderId:list) {
                reply.invoices.add(invoiceMapper.fromInternal(invoiceService.getInvoiceByOrderId(orderId)));        
            }
        }
        return reply;
    }
    
    @RequestMapping(path="/invoices/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedInvoiceListReply addInvoice( @RequestBody SeedInvoiceAddRequest req){
        SeedInvoiceListReply rep = new SeedInvoiceListReply();
        try{
           Invoice inv;
           inv = invoiceService.addInvoice(invoiceMapper.toInternal(req.invoice));
           rep.invoices.add(invoiceMapper.fromInternal(inv));
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding invoice. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/invoices/checkout",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedInvoiceListReply checkoutInvoice( @RequestBody SeedInvoiceAddRequest req){
        SeedInvoiceListReply rep = new SeedInvoiceListReply();
        try{           
                                //find invoice in database
            List<Appuser> users = userService.findUserBySessionId(req.sessionId);            
            if(users.isEmpty()) //if not exist, then we have not any orders 
                throw new Exception("This session id is undefined.");            
            if(users.size() > 1) //if exist, then extract userId, then OrderId for this user
                throw new Exception("Error add to basket. To users with equal sessId");            
            Appuser u = users.get(0);
            
            Invoice invoice = null;
            List<Invoice> invoices = (List<Invoice>)u.getInvoiceCollection();
            for(Invoice inv:invoices) {
                if(inv.getStatusId().getStatusId() == 0) {
                    invoice = inv;
                    break;
                }
            } 
            if(invoice == null) //if not exist, then we have not any orders
                throw new Exception("Invoices with statusId=0 did not find."); 
            
            Invoice inv = invoiceMapper.checkoutInvoice(invoice, req.invoice);
            inv = invoiceService.updateInvoice(inv);
            rep.invoices.add(invoiceMapper.fromInternal(inv));
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error updating invoice. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/invoices/del/{orderid}",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delInvoice(@PathVariable Long orderid){
        SeedGenericReply rep = new SeedGenericReply();
        try{
            invoiceService.delInvoice(orderid);
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete invoice. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
    @RequestMapping(path="/invoices/del/list",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delListInvoices(@RequestBody SeedDeleteForIdListRequest req){
        SeedGenericReply rep = new SeedGenericReply();
        try{
            Invoice inv;
            for(Long id:req.itemsId) {
                inv = invoiceService.getInvoiceByOrderId(id);
                inv.getUserId().getInvoiceCollection().remove(inv); //maybe its hack???
                invoiceService.delInvoice(id);
            }
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete invoice. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
}
