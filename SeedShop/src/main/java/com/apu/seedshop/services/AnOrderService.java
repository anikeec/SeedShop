/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.AnOrder;
import com.apu.seedshop.jpa.Invoice;
import com.apu.seedshop.repository.AnOrderRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AnOrderService {
    private static final Logger logger =  LoggerFactory.getLogger(AnOrderService.class);   

    @Autowired
    AnOrderRepository anOrderRepository;

    @Autowired
    InvoiceService invoiceService;
  
    public List<AnOrder> getAllAnOrders(){
        return  anOrderRepository.findAll();
    }

    public AnOrder getAnOrderById(Long id) throws IllegalArgumentException {
        if(id == null)    throw new IllegalArgumentException("id = null");
        AnOrder ao = anOrderRepository.findOne(id);
        return ao;
    }
    
    public List<AnOrder> getAnOrdersByOrderId(Long orderId) throws IllegalArgumentException { 
        if(orderId == null)    throw new IllegalArgumentException("orderId = null");
        Invoice invoice = invoiceService.getInvoiceByOrderId(orderId);
        List<AnOrder> ol = null;
        if(invoice != null) {
            ol = (List<AnOrder>)invoice.getAnOrderCollection();
        }        
        return ol;        
    }
    
    public AnOrder addAnOrder(AnOrder ao) throws IllegalArgumentException {    
        if(ao == null)    throw new IllegalArgumentException("ao = null");
        logger.debug(String.format("Adding anOrder %s, %s with id %s", 
                        ao.getBarcode(), ao.getAmount(), ao.getId()));
        ao = anOrderRepository.save(ao);
        return ao;
    }
    
    public void delAnOrder(Long id) throws IllegalArgumentException {
        if(id == null)    throw new IllegalArgumentException("id = null");
        AnOrder ao = anOrderRepository.findOne(id);
        if(ao!=null){
            logger.debug(String.format("Deleting anOrder %s, %s, %s, %s, with orderId %s", 
                    ao.getId(), 
                    ao.getBarcode(), 
                    ao.getPrice(),
                    ao.getAmount(),
                    ao.getOrderId()));
            anOrderRepository.delete(id);
        }
    }
    
}
