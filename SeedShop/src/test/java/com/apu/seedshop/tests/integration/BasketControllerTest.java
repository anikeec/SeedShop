package com.apu.seedshop.tests.integration;

import com.apu.seedshopapi.AddBasketRequest;
import com.apu.seedshopapi.AnProductItem;
import com.apu.seedshopapi.BasketListReply;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BasketControllerTest {
    @Autowired
    private MockMvc mockMvc;
        
    
    
    @Test
    public void addInvoiceTest() throws Exception{
        AddBasketRequest rq = new AddBasketRequest();
        rq.sessionId = "12345";
        rq.products = new ArrayList<AnProductItem>();
        AnProductItem item = new AnProductItem();
        item.barcode = "1";
        item.amount = 4;
        rq.products.add(item);
        item = new AnProductItem();
        item.barcode = "2";
        item.amount = 7;
        rq.products.add(item);
        
        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(rq);

        MvcResult result = mockMvc.perform(post("/basket/add")
                 .accept(MediaType.APPLICATION_JSON_UTF8)
                 .contentType(MediaType.APPLICATION_JSON_UTF8)
                 .content(content)
         )
           .andExpect(status().isOk())
         .andReturn();
         
        String reply = result.getResponse().getContentAsString();
        BasketListReply ir = om.readValue(reply, BasketListReply.class);
        assertEquals("Reurn code in not 0",ir.retcode.longValue(), 0L);
        /*
        if(ir.retcode==0){
            mockMvc.perform(delete("/invoices/del/"+ir.products.get(0).)
                                  .accept(MediaType.APPLICATION_JSON_UTF8)
                           )
                    .andExpect(status().isOk());                  
        }*/
    }
}
