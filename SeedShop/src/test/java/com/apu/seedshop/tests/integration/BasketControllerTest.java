package com.apu.seedshop.tests.integration;

import com.apu.seedshopapi.LoginReply;
import com.apu.seedshopapi.LoginRequest;
import com.apu.seedshopapi.SeedBasketAddRequest;
import com.apu.seedshopapi.SeedAnOrderItem;
import com.apu.seedshopapi.SeedBasketListReply;
import com.apu.seedshopapi.SeedBasketDeleteRequest;

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
import org.junit.Before;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BasketControllerTest {
//    public final static String AUTH_HTTP_HEADER ="X-Authorization";
//    private static String token = null;
    @Autowired
    private MockMvc mockMvc;
    
    private String testSessIdRead = "12345678901234567890123456789015";
    private String testSessId = "12345678901234567890123456789012";
    
//    @Before
//    public void login() throws Exception {
//        if(token!=null){
//            return;
//        }
//        LoginRequest rq = new LoginRequest();
//        rq.login = "librarian1";
//        rq.password = "qwerty";
//        ObjectMapper om = new ObjectMapper();
//        String content = om.writeValueAsString(rq);
//        MvcResult result = mockMvc.perform(post("/auth")
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(content)
//        )
//                .andExpect(status().isOk())
//                .andReturn();
//        String reply = result.getResponse().getContentAsString();
//        LoginReply lr = om.readValue(reply, LoginReply.class);
//        token = lr.token;
//    }
        
    @Test
    public void getBasketTest() throws Exception {
        this.mockMvc.perform(get("/basket/all/" + testSessIdRead)
//                                .header(AUTH_HTTP_HEADER, token)
                            )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"barcode\":\"1\"")));
    }
    
    @Test
    public void addNewBasketTest() throws Exception{
        SeedBasketAddRequest rq = new SeedBasketAddRequest();
        rq.sessionId = testSessId;
        rq.products = new ArrayList<SeedAnOrderItem>();
        SeedAnOrderItem item = new SeedAnOrderItem();
        item.barcode = "1";
        item.amount = 4;
        rq.products.add(item);
        item = new SeedAnOrderItem();
        item.barcode = "2";
        item.amount = 7;
        rq.products.add(item);
        
        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(rq);

        MvcResult result = mockMvc.perform(post("/basket/add")
                 .accept(MediaType.APPLICATION_JSON_UTF8)
                 .contentType(MediaType.APPLICATION_JSON_UTF8)
//                 .header(AUTH_HTTP_HEADER, token)
                 .content(content)
         )
           .andExpect(status().isOk())
         .andReturn();
         
        String reply = result.getResponse().getContentAsString();
        SeedBasketListReply ir = om.readValue(reply, SeedBasketListReply.class);
        assertEquals("Reurn code in not 0",ir.retcode.longValue(), 0L);
        
        SeedBasketDeleteRequest delrq = new SeedBasketDeleteRequest();
        delrq.sessionId = testSessId;  
        //delete all invoices with statusId=0 for current sessionID      
        om = new ObjectMapper();
        content = om.writeValueAsString(delrq);
        
        if(ir.retcode==0){
            mockMvc.perform(delete("/basket/del/invoice")
                            .accept(MediaType.APPLICATION_JSON_UTF8)
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
//                            .header(AUTH_HTTP_HEADER, token)
                            .content(content)
                           )
                    .andExpect(status().isOk());                  
        }
    }
    /*
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
    */
    //@Test
    public void addUpdateBasketTest() throws Exception{
        SeedBasketAddRequest rq = new SeedBasketAddRequest();
        rq.sessionId = testSessId;
        rq.products = new ArrayList<SeedAnOrderItem>();
        SeedAnOrderItem item = new SeedAnOrderItem();
        item.barcode = "1";
        item.amount = 4;
        rq.products.add(item);
        item = new SeedAnOrderItem();
        item.barcode = "2";
        item.amount = 7;
        rq.products.add(item);
        
        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(rq);

        MvcResult result = mockMvc.perform(post("/basket/add")
                 .accept(MediaType.APPLICATION_JSON_UTF8)
                 .contentType(MediaType.APPLICATION_JSON_UTF8)
//                 .header(AUTH_HTTP_HEADER, token)
                 .content(content)
         )
           .andExpect(status().isOk())
         .andReturn();
         
        String reply = result.getResponse().getContentAsString();
        SeedBasketListReply ir = om.readValue(reply, SeedBasketListReply.class);
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
