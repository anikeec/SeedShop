package com.apu.seedshop.services;

import com.apu.seedshopapi.SeedInvoice;
import com.apu.seedshop.jpa.Invoice;
import com.apu.seedshop.repository.InvoiceRepository;
import com.apu.seedshop.utils.EntityIdGenerator;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
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
    
    @Autowired
    DeliveryServiceService dServService;
    
    @Autowired
    DeliveryStatusService dStatService;
    
    @Autowired
    ProductLocationService plService;
    
    @Autowired
    UserService userService;
    
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
                si.delivery = inv.getDeliveryId().getDeliveryId();
            si.deliveryOffice = inv.getDeliveryOffice();
            si.prepayment = "" + inv.getPrepayment();
            si.status = inv.getStatusId().getStatusId();
            if(inv.getSourceId() != null) 
                si.sourceL = inv.getSourceId().getLocationId();
            if(inv.getDestinationId() != null) 
                si.destL = inv.getDestinationId().getLocationId();
            if(inv.getCurrentLocId() != null) 
                si.currL = inv.getCurrentLocId().getLocationId();
            if(inv.getBackorderId() != null) 
                si.backorderId = inv.getBackorderId().getOrderId();
            si.addInfoU = inv.getAddInfoU();
            si.addInfoM = inv.getAddInfoM();      
        }
        return si;
    }
    
/**
 * Creates new Invoice with good orderId
 * @return newly created Invoice with required fields set
 */
    private Invoice newInvoice() {
        //TODO: add setup userId
        Invoice inv = new Invoice();
        boolean idOK = false;
        Long orderId = 0l;
        while (!idOK) {
            orderId = EntityIdGenerator.random();
            idOK = !invoiceRepository.exists(orderId);
        }
        inv.setStatusId(dStatService.getDeliveryStatusById(0));
        inv.setOrderDate(new Date());
        return inv;
    }
    
 /**
 * Maps external REST model to internal Invoice;
 * If user does not exists in DB then creates new. If user already exists
 * then fetches user from DB and sets all fields from external REST model
 * @param su REST model
 * @return internal Users with all required fields set
 */
    public Invoice toInternal(SeedInvoice si) {
        Invoice inv = null;
        //first, check if it exists
        if (si.orderId != null) {
            inv = invoiceRepository.findOne(si.orderId);
        }
        if (inv == null) { //not found, create new
            logger.debug("Creating new invoice");
            inv = newInvoice();
        }
        logger.debug("Updating existing invoice");
        inv.setUserId(userService.getUserById(si.userId));
        inv.setFirstName(si.firstName);
        inv.setSecName(si.secName);
        inv.setThirdName(si.thirdName);
        DateFormat format = new SimpleDateFormat("d.MM.yyyy", Locale.ENGLISH);
        Date oDate = null;
        Date pDate = null;
        Date sDate = null;
        try { 
            oDate = format.parse(si.orderDate);
            pDate = format.parse(si.paidDate);
            sDate = format.parse(si.sentDate);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(InvoiceMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        inv.setOrderDate(oDate);
        inv.setPaidDate(pDate);
        inv.setSentDate(sDate);        
        inv.setDiscount(new BigDecimal(si.discount));
        inv.setPay(new BigDecimal(si.pay));
        inv.setStatusId(dStatService.getDeliveryStatusById(si.status));
        inv.setSourceId(plService.getProductLocationById(si.sourceL));
        inv.setDestinationId(plService.getProductLocationById(si.destL));
        inv.setCurrentLocId(plService.getProductLocationById(si.currL));
        inv.setDeliveryId(dServService.getDeliveryServiceById(si.delivery));
        inv.setDeliveryOffice(si.deliveryOffice);
        inv.setPrepayment(si.prepayment.equals("1"));
        inv.setDeclaration(si.declaration);      
        return inv;
    }

}
