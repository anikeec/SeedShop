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
    AppuserService userService;
    
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
            si.country = inv.getCountry();
            si.region = inv.getRegion();
            si.area = inv.getArea();
            si.city = inv.getCity();
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
    public Invoice newInvoice() {
        //TODO: add setup userId
        Invoice inv = new Invoice();
        boolean idOK = false;
        Long orderId = 0l;
        while (!idOK) {
            orderId = EntityIdGenerator.random();
            idOK = !invoiceRepository.exists(orderId);
        }
        inv.setOrderId(orderId);
        inv.setStatusId(dStatService.getDeliveryStatusById(0));
        inv.setOrderDate(new Date());
        inv.setDiscount(new BigDecimal(0));
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
        
        if (si.orderId != null) {   //first, check if it exists
            inv = invoiceRepository.findOne(si.orderId);
            
        }
        if(inv == null){            //not found, create new
            logger.debug("Creating new invoice");
            inv = newInvoice();
        }
        logger.debug("Updating existing invoice");
        
        //inv.setOrderId(si.orderId);
        inv.setFirstName(si.firstName);
        inv.setSecName(si.secName);
        inv.setThirdName(si.thirdName);
        inv.setDeclaration(si.declaration);  
        inv.setDeliveryOffice(si.deliveryOffice);
        inv.setPhone(si.phone);
        
        if(si.currL != null)
            inv.setCurrentLocId(plService.getProductLocationById(si.currL));
        else
            inv.setCurrentLocId(null);
        
        if(si.destL != null)
            inv.setDestinationId(plService.getProductLocationById(si.destL));
        else
            inv.setDestinationId(null);
        
        if(si.sourceL != null)
            inv.setSourceId(plService.getProductLocationById(si.sourceL));
        else
            inv.setSourceId(null);        
        
        if(si.country != null)
            inv.setCountry(si.country);
        else
            inv.setCountry(null);
        
        if(si.region != null)
            inv.setRegion(si.region);
        else
            inv.setRegion(null);
        
        if(si.area != null)
            inv.setArea(si.area);
        else
            inv.setArea(null);
        
        if(si.city != null)
            inv.setCity(si.city);
        else
            inv.setCity(null);
        
        if(si.delivery != null)
            inv.setDeliveryId(dServService.getDeliveryServiceById(si.delivery));
        else
            inv.setDeliveryId(null);
        
        if(si.status != null)
            inv.setStatusId(dStatService.getDeliveryStatusById(si.status));
        else
            inv.setStatusId(dStatService.getDeliveryStatusById(0));
        
        if(si.userId != null)
            inv.setUserId(userService.getUserById(si.userId));
        else
            inv.setUserId(null);
        
        DateFormat format = new SimpleDateFormat("d.MM.yyyy", Locale.ENGLISH);
        
        Date oDate = null;
        try { 
            if(si.orderDate != null) {
                oDate = format.parse(si.orderDate);
                inv.setOrderDate(oDate);
            }
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(InvoiceMapper.class.getName()).log(Level.SEVERE, null, ex);
        }         
        
        Date pDate = null;
        try {
            if(si.paidDate != null)     pDate = format.parse(si.paidDate);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(InvoiceMapper.class.getName()).log(Level.SEVERE, null, ex);
        } 
        inv.setPaidDate(pDate);
        
        Date sDate = null;
        try {
            if(si.sentDate != null)     sDate = format.parse(si.sentDate);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(InvoiceMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        inv.setSentDate(sDate);
        
        if(si.discount != null)
            inv.setDiscount(new BigDecimal(si.discount));
        else
            inv.setDiscount(null);
                
        if(si.pay != null)
            inv.setPay(new BigDecimal(si.pay));
        else
            inv.setPay(new BigDecimal(0));
        
        if(si.prepayment != null)
            inv.setPrepayment(si.prepayment.equals("true"));
        else
            inv.setPrepayment(false);

        return inv;
    }
    
    /**
 * Maps external REST model to internal Invoice;
 * If user does not exists in DB then creates new. If user already exists
 * then fetches user from DB and sets all fields from external REST model
 * @param inv REST model
 * @param si REST model
 * @return internal Users with all required fields set
 */
    public Invoice checkoutInvoice(Invoice inv, SeedInvoice si) { 
        logger.debug("Checkout invoice with orderId = " + inv.getOrderId());
        
        if(si.firstName != null)
            inv.setFirstName(si.firstName);
        
        if(si.secName != null)
            inv.setSecName(si.secName);
        
        if(si.thirdName != null)
            inv.setThirdName(si.thirdName);
        
        if(si.declaration != null)
            inv.setDeclaration(si.declaration); 
        
        if(si.deliveryOffice != null)
            inv.setDeliveryOffice(si.deliveryOffice);
        
        if(si.phone != null)
            inv.setPhone(si.phone);
        
        if(si.country != null)
            inv.setCountry(si.country);
        
        if(si.region != null)
            inv.setRegion(si.region);
        
        if(si.area != null)
            inv.setArea(si.area);
        
        if(si.city != null)
            inv.setCity(si.city);
        
        if(si.userId != null)
            inv.setUserId(userService.getUserById(si.userId));
        
        if(si.currL != null)
            inv.setCurrentLocId(plService.getProductLocationById(si.currL));
        
        if(si.destL != null)
            inv.setDestinationId(plService.getProductLocationById(si.destL));
        
        if(si.sourceL != null)
            inv.setSourceId(plService.getProductLocationById(si.sourceL));
        
        if(si.delivery != null)
            inv.setDeliveryId(dServService.getDeliveryServiceById(si.delivery));
        
        if(si.discount != null)
            inv.setDiscount(new BigDecimal(si.discount));
                
        if(si.pay != null)
            inv.setPay(new BigDecimal(si.pay));
        
        if(si.prepayment != null)
            inv.setPrepayment(si.prepayment.equals("true"));
        
        if(si.status != null)
            inv.setStatusId(dStatService.getDeliveryStatusById(si.status));
        else
            inv.setStatusId(dStatService.getDeliveryStatusById(1));
        
        DateFormat format = new SimpleDateFormat("d.MM.yyyy", Locale.ENGLISH);
        
        try { 
            if(si.orderDate != null){
                Date oDate = format.parse(si.orderDate);
                inv.setOrderDate(oDate);
            }                
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(InvoiceMapper.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        try {
            if(si.paidDate != null) {
                Date pDate = format.parse(si.paidDate);
                inv.setPaidDate(pDate);
            }
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(InvoiceMapper.class.getName()).log(Level.SEVERE, null, ex);
        }         
        
        try {
            if(si.sentDate != null) {
                Date sDate = format.parse(si.sentDate);
                inv.setSentDate(sDate);
            }
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(InvoiceMapper.class.getName()).log(Level.SEVERE, null, ex);
        }       

        return inv;
    }

}
