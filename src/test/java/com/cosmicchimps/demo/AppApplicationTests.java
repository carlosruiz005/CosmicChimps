package com.cosmicchimps.demo;

import com.cosmicchimps.demo.controller.ScheduleController;
import com.cosmicchimps.demo.dto.NewSchedule;
import com.cosmicchimps.demo.service.ScheduleService;
import com.cosmicchimps.demo.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
class AppApplicationTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ScheduleController scheduleController;
    @Autowired
    private ScheduleService scheduleService;

    @Test
    void contextLoads() {
        assertThat(scheduleController).isNotNull();
        assertThat(scheduleService).isNotNull();
    }

    @Test
    public void isEndDateBeforeStartDate() throws Exception {

        NewSchedule s = new NewSchedule(
                Utils.parseStringToLocalDate("2022-05-23"),
                Utils.parseStringToLocalDate("2022-05-13"),
                "WEEKLY",
                Arrays.asList("SU"));
        String ss = mapper.writeValueAsString(s);
        mvc
                .perform(post("/schedule/").content(ss)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.error").value("endDate cannot be before startDate"));

    }

    @Test
    public void doesDayListHaveDuplicate() throws Exception {
        NewSchedule s = new NewSchedule(
                Utils.parseStringToLocalDate("2022-05-23"),
                Utils.parseStringToLocalDate("2022-05-24"),
                "WEEKLY",
                Arrays.asList("SU", "SU"));
        String ss = mapper.writeValueAsString(s);
        mvc
                .perform(post("/schedule/").content(ss)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.dayList").value("dayList cannot have duplicated acronyms"));
    }

    @Test
    public void doesScheduleHaveAllFields() throws Exception {
        NewSchedule s = new NewSchedule(
                null,
                Utils.parseStringToLocalDate("2022-05-24"),
                "WEEKLY",
                Arrays.asList("SU", "SU"));
        String ss = mapper.writeValueAsString(s);
        mvc
                .perform(post("/schedule/").content(ss)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.dayList").value("dayList cannot have duplicated acronyms"));
    }

    @Test
    public void isDaylistEmpty() throws Exception {
        NewSchedule s = new NewSchedule(
                Utils.parseStringToLocalDate("2022-05-24"),
                Utils.parseStringToLocalDate("2022-05-24"),
                "WEEKLY",
                Arrays.asList());
        String ss = mapper.writeValueAsString(s);
        mvc
                .perform(post("/schedule/").content(ss)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.dayList").value("Empty is not allowed"));
    }

}
