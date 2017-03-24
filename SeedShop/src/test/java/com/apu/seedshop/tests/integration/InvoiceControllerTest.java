package com.apu.seedshop.tests.integration;

//import static com.apu.seedshop.tests.integration.BasketControllerTest.AUTH_HTTP_HEADER;
import com.apu.seedshopapi.LoginReply;
import com.apu.seedshopapi.LoginRequest;
import com.apu.seedshopapi.SeedInvoiceAddRequest;
import com.apu.seedshopapi.SeedInvoice;
import com.apu.seedshopapi.SeedInvoiceListReply;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class InvoiceControllerTest {
//    public final static String AUTH_HTTP_HEADER ="X-Authorization";
//    private static String token = null;
    @Autowired
    private MockMvc mockMvc;
    
    private String testSessId = "12345678901234567890123456789015";
    
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
    public void findInvoiceTest() throws Exception {
        this.mockMvc.perform(get("/invoices/all")
//                                .header(AUTH_HTTP_HEADER, token)
                            )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Петров")));
    }
    
    @Test
    public void findInvoiceByOrderIdTest() throws Exception {
        this.mockMvc.perform(get("/invoices/byid/1")
//                                .header(AUTH_HTTP_HEADER, token)
                            )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Сидоров")));
    }
    
    @Test
    public void findInvoiceBySessionIdTest() throws Exception {
        this.mockMvc.perform(get("/invoices/bysessid/" + testSessId)
//                                .header(AUTH_HTTP_HEADER, token)
        )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Сидоров")));
    }
    
    @Test
    public void addInvoiceTest() throws Exception{
        SeedInvoiceAddRequest rq = new SeedInvoiceAddRequest();
        rq.invoice = new SeedInvoice();
        rq.invoice.orderId = 100l;
        rq.invoice.userId = 1l;
        rq.invoice.orderDate = "01.02.2017";
        rq.invoice.paidDate = "02.02.2017";
        rq.invoice.sentDate = "03.02.2017";
        rq.invoice.discount = "0.5";
        rq.invoice.pay = "0.3";
        rq.invoice.secName = "A";
        rq.invoice.firstName = "P";
        rq.invoice.thirdName = "Y";
        rq.invoice.phone = "+380502103706";
        rq.invoice.declaration = "1234567890";
        rq.invoice.delivery = 1;
        rq.invoice.deliveryOffice = 1;
        rq.invoice.prepayment = "1";
        rq.invoice.status = 0;
        rq.invoice.sourceL = 1;
        rq.invoice.destL = 2;
        rq.invoice.currL = 3;
        rq.invoice.addInfoU = "Test user info";
        rq.invoice.addInfoM = "Test manager info";
        
        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(rq);

        MvcResult result = mockMvc.perform(post("/invoices/add")
                 .accept(MediaType.APPLICATION_JSON_UTF8)
                 .contentType(MediaType.APPLICATION_JSON_UTF8)
//                 .header(AUTH_HTTP_HEADER, token)
                 .content(content)
         )
           .andExpect(status().isOk())
         .andReturn();
         
        String reply = result.getResponse().getContentAsString();
        SeedInvoiceListReply ir = om.readValue(reply, SeedInvoiceListReply.class);
        assertEquals("Reurn code in not 0",ir.retcode.longValue(), 0L);
        if(ir.retcode==0){
            mockMvc.perform(delete("/invoices/del/"+ir.invoices.get(0).orderId)
                                  .accept(MediaType.APPLICATION_JSON_UTF8)
//                                  .header(AUTH_HTTP_HEADER, token)
                           )
                    .andExpect(status().isOk());                  
        }
    }
}
