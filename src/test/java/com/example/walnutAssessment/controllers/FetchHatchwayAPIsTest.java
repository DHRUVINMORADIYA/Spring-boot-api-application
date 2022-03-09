package com.example.walnutAssessment.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FetchHatchwayAPIsTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FetchHatchwayAPIs fetchHatchwayAPIs;

    @Test
    void pingController() throws Exception {
        mockMvc.perform(get("/api/ping"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetError_whenTagsMissing() throws Exception {

        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Tags parameter is required\"}"));
    }

    @Test
    void shouldGetError_whenSortByIsInvalid() throws Exception {

        mockMvc.perform(get("/api/posts?tags=tech&sortBy=author"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"sortBy parameter is invalid\"}"));
    }

    @Test
    void shouldGetError_whenDirectionIsInvalid() throws Exception {

        mockMvc.perform(get("http://localhost:8080/api/posts?tags=tech&sortBy=id&direction=as"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"direction is invalid\"}"));
    }

}