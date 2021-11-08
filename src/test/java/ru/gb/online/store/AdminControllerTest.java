package ru.gb.online.store;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.gb.online.store.entities.Product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gb.online.store.utils.Mapper.asJsonString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    /**
     Задание 4.
     Spring boot test (доступ к добалению товара имеет только админ,
     при неспешной авторизации 403 ошибка)
     */

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("admin")
    public void addProductAdminTest() throws Exception {
        this.mockMvc.perform(
                post("/admin/addNewProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new Product ())))
                .andExpect(status ().isOk ());
    }

    @Test
    @WithUserDetails("user")
    public void addProductUserTest() throws Exception {
        this.mockMvc.perform(post("/admin/addNewProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new Product ())))
                .andExpect(status ().isForbidden ());
    }

}
