package com.cosmicchimps.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
class AppApplicationTests {

//    @Autowired
//    private MockMvc mvc;

    @Test
    void contextLoads() {
    }

//    public void isEndDateBeforeStartDate() throws Exception {
//        mvc
//                .perform(get("/dates/create"))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$.valor").value("endDate cannot be before than starDate."));
//
//    }

    public void doesDayListHaveDuplicate() {
    }

    public void doesScheduleHaveAllFields() {
    }

    public void isDaylistEmpty() {
    }

}
