package com.apu.seedshop.tests.integration;

import com.apu.seedshopapi.AddBasketRequest;
import com.apu.seedshopapi.AnOrderItem;
import com.apu.seedshopapi.BasketListReply;
import com.apu.seedshopapi.DeleteBasketRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
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
    
    private String testSessIdRead = "12345678901234567890123456789015";
    private String testSessId = "12345678901234567890123456789012";
        
    @Test
    public void getBasketTest() throws Exception {
        this.mockMvc.perform(get("/basket/all/" + testSessIdRead))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"barcode\":\"1\"")));
    }
    
    @Test
    public void addNewBasketTest() throws Exception{
        AddBasketRequest rq = new AddBasketRequest();
        rq.sessionId = testSessId;
        rq.products = new ArrayList<AnOrderItem>();
        AnOrderItem item = new AnOrderItem();
        item.barcode = "1";
        item.amount = 4;
        rq.products.add(item);
        item = new AnOrderItem();
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
        
        DeleteBasketRequest delrq = new DeleteBasketRequest();
        delrq.sessionId = testSessId;  
        //delete all invoices with statusId=0 for current sessionID      
        om = new ObjectMapper();
        content = om.writeValueAsString(delrq);
        
        if(ir.retcode==0){
            mockMvc.perform(delete("/basket/del/invoice")
                            .accept(MediaType.APPLICATION_JSON_UTF8)
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(content)
                           )
                    .andExpect(status().isOk());                  
        }
    }
    
    @Test
    public void deleteOrdersFromBasketTest() throws Exception{        
        AddBasketRequest rq = new AddBasketRequest();
        rq.sessionId = testSessId;
        rq.products = new ArrayList<AnOrderItem>();
        AnOrderItem item = new AnOrderItem();
        item.barcode = "1";
        item.amount = 4;
        rq.products.add(item);
        item = new AnOrderItem();
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
        
        List<Long> ordersList = new ArrayList<>();
        if(ir.orderItems != null) {
            for(AnOrderItem anOrderItem:ir.orderItems) {
                ordersList.add(anOrderItem.id);
            }
        }        
        //test delete list of orders
        DeleteBasketRequest delrq = new DeleteBasketRequest();
        delrq.sessionId = testSessId; 
        if(!ordersList.isEmpty()) {
            delrq.ordersId = ordersList;
        }
        om = new ObjectMapper();
        content = om.writeValueAsString(delrq);
        
        if(ir.retcode==0){
            mockMvc.perform(delete("/basket/del/order")
                            .accept(MediaType.APPLICATION_JSON_UTF8)
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(content)
                           )
                    .andExpect(content().string(containsString("\"error_message\":null")));                  
        }
        
        //TODO - delete new created user with invoice        
    }
    
    //@Test
    public void addUpdateBasketTest() throws Exception{
        AddBasketRequest rq = new AddBasketRequest();
        rq.sessionId = testSessId;
        rq.products = new ArrayList<AnOrderItem>();
        AnOrderItem item = new AnOrderItem();
        item.barcode = "1";
        item.amount = 4;
        rq.products.add(item);
        item = new AnOrderItem();
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
