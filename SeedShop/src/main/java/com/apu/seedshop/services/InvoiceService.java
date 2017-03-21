/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.AnOrder;
import com.apu.seedshop.jpa.Invoice;
import com.apu.seedshop.repository.InvoiceRepository;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
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

    public Invoice getInvoiceByOrderId(Long orderId) throws IllegalArgumentException {
        if(orderId == null)    throw new IllegalArgumentException("orderId = null");
        Invoice i = invoiceRepository.findOne(orderId);
        return i;
    }
    
    public List<Invoice> getInvoiceByUserId(Long userId) throws IllegalArgumentException {
        if(userId == null)    throw new IllegalArgumentException("userId = null");
        List<Invoice> list = invoiceRepository.findByUserId(userId);
        return list;
    }
    
    public Invoice addInvoice(Invoice inv) throws IllegalArgumentException {
        if(inv == null)    throw new IllegalArgumentException("inv = null");
        logger.debug("Add invoice - userId = "
                        + inv.getUserId().getUserId()
                        + ", orderId = " + inv.getOrderId()); 
        inv = invoiceRepository.save(inv);
        return inv;
    }
    
    public Invoice updateInvoice(Invoice inv) throws IllegalArgumentException {
        if(inv == null)    throw new IllegalArgumentException("inv = null");
        logger.debug("Update invoice - orderId = " + inv.getOrderId()); 
        //Invoice oldInv = getInvoiceByOrderId(oldInvId);
        
//        if(inv.getCurrentLocId() != null)
//            oldInv.setCurrentLocId(inv.getCurrentLocId());
//        if(inv.getDeclaration() != null)
//            oldInv.setDeclaration(inv.getDeclaration()); 
//        if(inv.getDeliveryId() != null)
//            oldInv.setDeliveryId(inv.getDeliveryId());
//        if(inv.getDeliveryOffice() != null)
//            oldInv.setDeliveryOffice(inv.getDeliveryOffice());
//        if(inv.getDestinationId() != null)
//            oldInv.setDestinationId(inv.getDestinationId());
//        if(inv.getDiscount() != null)
//            oldInv.setDiscount(inv.getDiscount());
//        if(inv.getFirstName() != null)
//            oldInv.setFirstName(inv.getFirstName());
//        if(inv.getOrderDate() != null)
//            oldInv.setOrderDate(inv.getOrderDate());
//        //if(inv.getOrderId() != null)
//        //    oldInv.setOrderId(inv.getOrderId());
//        if(inv.getPaidDate() != null)
//            oldInv.setPaidDate(inv.getPaidDate());
//        if(inv.getPay() != null)
//            oldInv.setPay(inv.getPay());
//        if(inv.getPhone() != null)
//            oldInv.setPhone(inv.getPhone());
//        if(inv.getPrepayment() != null)
//            oldInv.setPrepayment(inv.getPrepayment());
//        if(inv.getSecName() != null)
//            oldInv.setSecName(inv.getSecName());
//        if(inv.getSentDate() != null)
//            oldInv.setSentDate(inv.getSentDate());
//        if(inv.getStatusId() != null)
//            oldInv.setStatusId(inv.getStatusId());
//        if(inv.getSourceId() != null)
//            oldInv.setSourceId(inv.getSourceId());
//        if(inv.getThirdName() != null)
//            oldInv.setThirdName(inv.getThirdName());
//        if(inv.getUserId() != null)
//            oldInv.setUserId(inv.getUserId());     
//        
        inv = invoiceRepository.save(inv);
        return inv;
    }

    public void delInvoice(Long orderId) throws IllegalArgumentException {
        if(orderId == null)    throw new IllegalArgumentException("orderId = null");
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
