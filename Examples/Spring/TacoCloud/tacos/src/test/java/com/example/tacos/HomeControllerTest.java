package com.example.tacos;

import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;

import com.example.tacos.controllers.HomeController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.*;
import org.springframework.test.web.servlet.*;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception
    {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/")
        )
        .andExpect(
            MockMvcResultMatchers.status().isOk()
        )
        .andExpect(
            MockMvcResultMatchers.view().name("home")
        )
        .andExpect(
            MockMvcResultMatchers.content().string(
                Matchers.containsString("Welcome to")
            )
        );
    }
}
