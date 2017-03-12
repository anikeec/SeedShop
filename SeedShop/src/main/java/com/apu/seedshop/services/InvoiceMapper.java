package com.apu.seedshop.services;

import com.apu.seedshopapi.SeedInvoice;
import com.apu.seedshop.jpa.Invoice;
import com.apu.seedshop.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class InvoiceMapper {
    private static final Logger logger =  LoggerFactory.getLogger(InvoiceMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    
    @Autowired
    InvoiceRepository invoiceRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param i internal invoice model
 * @return external REST invoice model
 */
    public SeedInvoice fromInternal(Invoice inv) {
        SeedInvoice si = null;
        if (inv != null) {
            si = new SeedInvoice();          
            si.orderId = inv.getOrderId();
            si.userId = inv.getUserId().getUserId();
            si.orderDate = "" + inv.getOrderDate();
            si.paidDate = "" + inv.getPaidDate();
            si.sentDate = "" + inv.getSentDate();
            si.discount = "" + inv.getDiscount();
            si.pay = "" + inv.getPay();
            si.secName = inv.getSecName();
            si.firstName = inv.getFirstName();
            si.thirdName = inv.getThirdName();
            si.phone = inv.getPhone();
            si.declaration = inv.getDeclaration();
            if(inv.getDeliveryId() != null)
                si.delivery = inv.getDeliveryId().getName();
            si.deliveryOffice = inv.getDeliveryOffice();
            si.prepayment = "" + inv.getPrepayment();
            si.status = inv.getStatusId().getStatus();
            if(inv.getSourceId() != null) 
                si.sourceL = "" + inv.getSourceId().getName();
            if(inv.getDestinationId() != null) 
                si.destL = "" + inv.getDestinationId().getName();
            if(inv.getCurrentLocId() != null) 
                si.currL = "" + inv.getCurrentLocId().getName();
            si.addInfoU = inv.getAddInfoU();
            si.addInfoM = inv.getAddInfoM();      
        }
        return si;
    }

}
