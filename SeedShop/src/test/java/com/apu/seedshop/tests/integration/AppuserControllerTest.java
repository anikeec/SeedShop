package com.apu.seedshop.tests.integration;

import com.apu.seedshopapi.LoginReply;
import com.apu.seedshopapi.LoginRequest;
import com.apu.seedshopapi.SeedUserAddRequest;
import com.apu.seedshopapi.SeedGenericReply;
import com.apu.seedshopapi.SeedUser;
import com.apu.seedshopapi.SeedUserListReply;
import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Before;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AppuserControllerTest {
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
    public void findUserTest() throws Exception {
        this.mockMvc.perform(get("/users/byid/1")
//                                .header(AUTH_HTTP_HEADER, token)
                                )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Петров")));
    }
    
    @Test
    public void addUserTest() throws Exception{
        SeedUserAddRequest rq = new SeedUserAddRequest();
        rq.user = new SeedUser();
        rq.user.userId = 100l;
        rq.user.login = "";
        rq.user.secName = "A";
        rq.user.firstName = "P";
        rq.user.thirdName = "Y";
        rq.user.email = "apu";
        rq.user.phones = "+3";
        rq.user.discount = "0.5";
        rq.user.birthday = "01.02.2017";
        rq.user.country = "U";
        rq.user.region = "C";
        rq.user.area = "C";
        rq.user.city = "C";
        rq.user.genderId = 0;
        rq.user.temp = "true";
        rq.user.used = "true";
        
        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(rq);

        MvcResult result = mockMvc.perform(post("/users/add")
                 .accept(MediaType.APPLICATION_JSON_UTF8)
                 .contentType(MediaType.APPLICATION_JSON_UTF8)
//                 .header(AUTH_HTTP_HEADER, token)
                 .content(content)
         )
           .andExpect(status().isOk())
         .andReturn();
         
        String reply = result.getResponse().getContentAsString();
        SeedUserListReply ur = om.readValue(reply, SeedUserListReply.class);
        assertEquals("Return code in not 0",ur.retcode.longValue(), 0L);
        if(ur.retcode==0){
            result= mockMvc.perform(delete("/users/del/"+ur.users.get(0).userId)
                                    .accept(MediaType.APPLICATION_JSON_UTF8)
//                                    .header(AUTH_HTTP_HEADER, token)
                           )
                    .andExpect(status().isOk())
                    .andReturn(); 
            reply = result.getResponse().getContentAsString();
            SeedGenericReply gr = om.readValue(reply, SeedGenericReply.class);
            assertEquals("Return code in not 0. Delete false.",gr.retcode.longValue(), 0L);
        }
    }
}
