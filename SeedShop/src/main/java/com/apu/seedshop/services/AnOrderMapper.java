package com.apu.seedshop.services;

import com.apu.seedshopapi.SeedAnOrder;
import com.apu.seedshop.jpa.AnOrder;
import com.apu.seedshop.repository.AnOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class AnOrderMapper {
    private static final Logger logger =  LoggerFactory.getLogger(AnOrderMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    
    @Autowired
    AnOrderRepository anOrderRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param ao internal AnOrder model
 * @return external REST SeedAnOrder model
 */
    public SeedAnOrder fromInternal(AnOrder ao) {
        SeedAnOrder sao = null;
        if (ao != null) {
            sao = new SeedAnOrder();          
            sao.id = ao.getId();
            sao.orderId = ao.getOrderId().getOrderId();
            sao.barcode = ao.getBarcode().getBarcode();
            sao.price = "" + ao.getPrice();
            sao.amount = ao.getAmount();     
        }
        return sao;
    }

}
