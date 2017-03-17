package com.apu.seedshop.tests.integration;

import com.apu.seedshopapi.AddUserRequest;
import com.apu.seedshopapi.GenericReply;
import com.apu.seedshopapi.SeedUser;
import com.apu.seedshopapi.UserListReply;
import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.CoreMatchers.containsString;
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
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
        
    @Test
    public void findUserTest() throws Exception {
        this.mockMvc.perform(get("/users/byid/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Петров")));
    }
    
    @Test
    public void addUserTest() throws Exception{
        AddUserRequest rq = new AddUserRequest();
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
        rq.user.gender = "M";
        rq.user.temp = "true";
        rq.user.used = "true";
        
        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(rq);

        MvcResult result = mockMvc.perform(post("/users/add")
                 .accept(MediaType.APPLICATION_JSON_UTF8)
                 .contentType(MediaType.APPLICATION_JSON_UTF8)
                 .content(content)
         )
           .andExpect(status().isOk())
         .andReturn();
         
        String reply = result.getResponse().getContentAsString();
        UserListReply ur = om.readValue(reply, UserListReply.class);
        assertEquals("Return code in not 0",ur.retcode.longValue(), 0L);
        if(ur.retcode==0){
            result= mockMvc.perform(delete("/users/del/"+ur.users.get(0).userId)
                                    .accept(MediaType.APPLICATION_JSON_UTF8)
                           )
                    .andExpect(status().isOk())
                    .andReturn(); 
            reply = result.getResponse().getContentAsString();
            GenericReply gr = om.readValue(reply, GenericReply.class);
            assertEquals("Return code in not 0. Delete false.",gr.retcode.longValue(), 0L);
        }
    }
}
