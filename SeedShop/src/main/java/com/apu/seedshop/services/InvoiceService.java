/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.AnOrder;
import com.apu.seedshop.jpa.Invoice;
import com.apu.seedshop.repository.InvoiceRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InvoiceService {
    private static final Logger logger =  LoggerFactory.getLogger(InvoiceService.class);   

    @Autowired
    InvoiceRepository invoiceRepository;
  
    public List<Invoice> getAllInvoices(){
        return  invoiceRepository.findAll();
    }

    public Invoice getInvoiceByOrderId(Long orderId) {
        Invoice i = invoiceRepository.findByOrderId(orderId).get(0);
        return i;
    }
    
    public void delInvoice(Long orderId){
        if(invoiceRepository.findByOrderId(orderId).isEmpty()) {
            logger.debug("Try delete invoice - "
                                + "orderId = " + orderId
                                + ". This orderId is absent.");
            return;
        }
        Invoice inv = invoiceRepository.findByOrderId(orderId).get(0);
        
        List<AnOrder> orders = (List<AnOrder>)inv.getAnOrderCollection();
        for(AnOrder ao: orders) {
            if(ao != null) {                       
                logger.debug("Deleting anOrder - "
                        + "id = "+ ao.getId() 
                        + ", orderId = " + ao.getOrderId().getOrderId()
                        + ", barcode = " + ao.getBarcode().getBarcode());
                //anOrderService.delAnOrder(ao.getId());
            }
        }
        List<Invoice> origInvoices = (List<Invoice>)inv.getInvoiceCollection();
        if(!origInvoices.isEmpty()) {
            for(Invoice origInv: origInvoices) {
                orders = (List<AnOrder>)origInv.getAnOrderCollection();
                for(AnOrder ao: orders) {
                    if(ao != null) {                       
                        logger.debug("Deleting anOrder - "
                                + "id = "+ ao.getId() 
                                + ", orderId = " + ao.getOrderId().getOrderId()
                                + ", barcode = " + ao.getBarcode().getBarcode());
                    }
                }
                logger.debug("Deleting origin invoice - orderId = " 
                                + origInv.getOrderId()
                                + ", backorderId = " 
                                + origInv.getBackorderId().getOrderId());
            }
        }
        logger.debug("Deleting invoice - userId = "
                        + inv.getUserId().getUserId()
                        + ", orderId = " + inv.getOrderId());                
        invoiceRepository.delete(inv.getOrderId());
    }
    
}
