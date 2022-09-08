package com.sweetTreats.Sweet_Treats_With_Spring_Boot;

import com.sweetTreats.Sweet_Treats_With_Spring_Boot.service.CourierService;
import com.sweetTreats.Sweet_Treats_With_Spring_Boot.service.OrderService;
import com.sweetTreats.Sweet_Treats_With_Spring_Boot.web.Controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebMvcTest(Controller.class)
//@AutoConfigureMockMvc
class SweetTreatsWithSpringBootApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CourierService courierService;
    @MockBean
    OrderService orderService;


    @Test
    void getAllCouriers() throws Exception {
        this.mockMvc.perform(get("/sweet_treats/couriers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    public void findOne() throws Exception {
        mockMvc.perform(get("/sweet_treats/courier/1"))
                .andDo(print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect((ResultMatcher) jsonPath("$.id", is(1)));
    }
}
