/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.Invoice;
import com.apu.seedshop.services.InvoiceMapper;
import com.apu.seedshop.services.InvoiceService;
import com.apu.seedshopapi.GenericReply;
import com.apu.seedshopapi.InvoiceListReply;
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
    
    @RequestMapping(path="/invoices/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public InvoiceListReply getAllInvoices(){
        InvoiceListReply reply = new InvoiceListReply();
        for(Invoice u: invoiceService.getAllInvoices()){
           reply.invoices.add(invoiceMapper.fromInternal(u));    
        }
        return reply;
    }
    
    @RequestMapping(path="/invoices/byid/{orderid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public InvoiceListReply getInvoiceByOrderId(@PathVariable Long orderid){
        InvoiceListReply reply = new InvoiceListReply();
        reply.invoices.add(invoiceMapper.fromInternal(invoiceService.getInvoiceByOrderId(orderid)));        
        return reply;
    }
    
    @RequestMapping(path="/invoices/del/{orderid}",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delInvoice(@PathVariable Long orderid){
        GenericReply rep = new GenericReply();
        try{
            invoiceService.delInvoice(orderid);
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete invoice. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
}
