/*
 * 
 * 
 */
package com.apu.seedshop.services;
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

    public Invoice getInvoiceByOrderId(Integer orderId) {
        Invoice i = invoiceRepository.findByOrderId(orderId).get(0);
        return i;
    }
    /*
    public void delInvoice(Integer orderId){
        List<AnOrder> aol = anOrderRepository.findByOrderId(orderId);
        for(AnOrder ao: aol){
            if(ao!=null){
                logger.debug(String.format("Deleting anOrder %s, %s, %s, %s, with orderId %s", 
                        ao.getId(), 
                        ao.getBarcode(), 
                        ao.getPrice(),
                        ao.getAmount(),
                        ao.getOrderId()));
                int id = ao.getId();
                anOrderRepository.delete(id);
            }
        }
    }
    */
}
