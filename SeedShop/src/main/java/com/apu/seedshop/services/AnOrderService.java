/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.AnOrder;
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

    public AnOrder getAnOrderById(Integer id) {
        AnOrder ao = anOrderRepository.findById(id).get(0);
        return ao;
    }
    
    public List<AnOrder> getAnOrdersByOrderId(Integer orderId) {
        List<AnOrder> ol = (List<AnOrder>) invoiceService.getInvoiceByOrderId(orderId).getAnOrderCollection();
        return ol;        
        //return anOrderRepository.findByOrderId(invoiceService.getInvoiceByOrderId(orderId));
    }
    
    public void delAnOrder(Integer id){
        AnOrder ao = anOrderRepository.findById(id).get(0);
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
