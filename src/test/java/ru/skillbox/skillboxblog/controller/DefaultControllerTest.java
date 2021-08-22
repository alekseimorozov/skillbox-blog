package ru.skillbox.skillboxblog.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DefaultController.class)
class DefaultControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("When '/' is requested index.html is returned")
    public void indexTest() throws Exception {
        mvc.perform(get("/"))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
    }
}