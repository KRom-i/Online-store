package ru.gb.online.store;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Parameters;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OnlineStoreAppMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Интернет-магазин")));
    }

    @Test
    public void admin() throws Exception{
        this.mockMvc.perform (formLogin ("/authenticateTheUser").user ("admin").password ("12345"))
                .andDo (print ())
                .andExpect (status ().is3xxRedirection ());

//        this.mockMvc.perform (get ("/admin"))
//                .andDo (print ())
//                .andExpect (status ().isOk ());
    }

    @Test
    @WithUserDetails("admin")
    @Parameters("123123")
    public void test2(String url) throws Exception {
        this.mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status ().isOk ());
        System.out.println (url);
    }

    @Test
    @WithUserDetails("user")
    public void test3() throws Exception {
        this.mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status ().isForbidden ());
    }


}
