package com.sbab.dev.rest;


import com.sbab.dev.domain.ApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@ActiveProfiles("test")
public class ControllerTests {

    @Mock
    private ApiService apiService;


    @InjectMocks
    private Controller controller;


    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }


    @Test
    void findLinesWithMostStopsTest() throws Exception {
        when(apiService.FindTopLines(anyInt())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/toplines"))
                .andExpect(status().isOk());
        verify(apiService).FindTopLines(10);
    }

    @Test
    void findStopPointsTests() throws Exception {
        when(apiService.findStopsForTopLines(anyString())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/stopsbyname/636"))
                .andExpect(status().isOk());
        verify(apiService).findStopsForTopLines("636");
    }

}
