package com.apu.seedshop.services;

import com.apu.seedshop.jpa.AnOrder;
import com.apu.seedshop.jpa.Product;
import com.apu.seedshop.repository.UserGenderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.apu.seedshop.utils.EntityIdGenerator;
import com.apu.seedshop.utils.HashGenerator;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import com.apu.seedshop.repository.AppuserRepository;
import com.apu.seedshopapi.SeedBasketItem;
import com.apu.seedshopapi.SeedBasketListReply;
import com.apu.seedshopapi.SeedProduct;
import java.util.List;

@Component

public class BasketMapper {
    private static final Logger logger =  LoggerFactory.getLogger(BasketMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    @Autowired
    AppuserRepository userRepository;
    
    @Autowired
    UserGenderRepository ugRepository;
    
    @Autowired
    ProductService productService;
    
/**
 * Maps internal JPA model to external REST model
 * @param orders internal AnOrder model
 * @return external REST BasketListReply model
 */
    public SeedBasketListReply fromInternal(List<AnOrder> orders) {
        SeedBasketListReply rep = new SeedBasketListReply();
        SeedBasketItem item; 
        Product product;
        for(AnOrder order:orders){
            item = new SeedBasketItem();                   
            item.product = new SeedProduct();
            item.product.barcode = order.getBarcode().getBarcode();                    
            item.orderId = "" + order.getId();                    
            item.count = order.getAmount();
            product = productService.getProductByBarcode(item.product.barcode);
            item.product.name = product.getProductId().getName();
            item.product.manufact = product.getManufactId().getName();
            item.product.pack = product.getPackingId().getPackId().getName();
            item.product.weight = "" + product.getPackingId().getWeight();
            item.product.amount = "" + product.getPackingId().getAmount();
            item.product.price = "" + product.getPrice();                            
            rep.basketItems.add(item);
        }        
        return rep;
    }
    

}
