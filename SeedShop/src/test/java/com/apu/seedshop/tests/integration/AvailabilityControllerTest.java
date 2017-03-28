package com.apu.seedshop.tests.integration;

import com.apu.seedshop.tests.TestId;
import com.apu.seedshopapi.LoginReply;
import com.apu.seedshopapi.LoginRequest;
import com.apu.seedshopapi.SeedAvailability;
import com.apu.seedshopapi.SeedGenericReply;

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
public class AvailabilityControllerTest {
//    public final static String AUTH_HTTP_HEADER ="X-Authorization";
//    private static String token = null;
    @Autowired
    private MockMvc mockMvc;
    
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
    public void getAllAvailabilitiesTest() throws Exception {
        this.mockMvc.perform(get("/avail/all/")
//                                .header(AUTH_HTTP_HEADER, token)
                            )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"barcode\":\"1\"")));
    }
    
    @Test
    public void getAvailabilityByIdTest() throws Exception {
        this.mockMvc.perform(get("/avail/byid/" + TestId.TestIdAvailContrAvailId)
//                                .header(AUTH_HTTP_HEADER, token)
                            )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"barcode\":\"1\"")));
    }
    
    @Test
    public void addDelNewAvailabilityTest() throws Exception{
        SeedAvailability rq = new SeedAvailability();
        rq.barcode = TestId.TestIdAvailContrProdBarcode;
        rq.locationId = TestId.TestIdAvailContrLocId;
        rq.id = TestId.TestIdAvailContrAvailIdNew;
        rq.available = 10;
        rq.reserv = 20;        
        
        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(rq);

        MvcResult result = mockMvc.perform(post("/avail/add")
                 .accept(MediaType.APPLICATION_JSON_UTF8)
                 .contentType(MediaType.APPLICATION_JSON_UTF8)
//                 .header(AUTH_HTTP_HEADER, token)
                 .content(content)
         )
           .andExpect(status().isOk())
         .andReturn();
         
        String reply = result.getResponse().getContentAsString();
        SeedGenericReply ir = om.readValue(reply, SeedGenericReply.class);
        assertEquals("Add. Return code in not 0",ir.retcode.longValue(), 0L);
        
        if(ir.retcode==0){
            result = mockMvc.perform(delete("/avail/del/" + 
                                                TestId.TestIdAvailContrAvailIdNew)
                            .accept(MediaType.APPLICATION_JSON_UTF8)
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
//                            .header(AUTH_HTTP_HEADER, token)
                           )
                    .andExpect(status().isOk())
                    .andReturn();
            reply = result.getResponse().getContentAsString();
            ir = om.readValue(reply, SeedGenericReply.class);
            assertEquals("Delete. Return code in not 0",ir.retcode.longValue(), 0L);
        }
    }
}
