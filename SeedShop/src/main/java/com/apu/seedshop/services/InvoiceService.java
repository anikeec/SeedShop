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
        Invoice i = invoiceRepository.findOne(orderId);
        return i;
    }
    
    public List<Invoice> getInvoiceByUserId(Long userId) {
        List<Invoice> list = invoiceRepository.findByUserId(userId);
        return list;
    }
    
    public Invoice addInvoice(Invoice inv) {
        logger.debug("Add invoice - userId = "
                        + inv.getUserId().getUserId()
                        + ", orderId = " + inv.getOrderId()); 
        inv = invoiceRepository.save(inv);
        return inv;
    }

    public void delInvoice(Long orderId){
        if(invoiceRepository.findOne(orderId) == null) {
            logger.debug("Try delete invoice - "
                                + "orderId = " + orderId
                                + ". This orderId is absent.");
            return;
        }
        Invoice inv = invoiceRepository.findOne(orderId);        
        logger.debug("Deleting invoice - userId = "
                        + inv.getUserId().getUserId()
                        + ", orderId = " + inv.getOrderId());
        invoiceRepository.delete(orderId);
    }
    
//    public void delInvoices(List<Long> ordersId){
//        for(int i=0;i<ordersId.size();i++){
//            if(invoiceRepository.findOne(ordersId.get(i)) == null) {
//                logger.debug("Try delete invoice - "
//                                    + "orderId = " + ordersId.get(i)
//                                    + ". This orderId is absent.");
//                return;
//            }
//            Invoice inv = invoiceRepository.findOne(ordersId.get(i));        
//            logger.debug("Deleting invoice - userId = "
//                            + inv.getUserId().getUserId()
//                            + ", orderId = " + inv.getOrderId());
//            invoiceRepository.delete(ordersId.get(i));
//        }
//    }
    
}
