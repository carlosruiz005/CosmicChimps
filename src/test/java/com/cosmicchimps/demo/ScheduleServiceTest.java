package com.cosmicchimps.demo;

import com.cosmicchimps.demo.controller.ScheduleController;
import com.cosmicchimps.demo.dto.NewSchedule;
import com.cosmicchimps.demo.entity.Schedule;
import com.cosmicchimps.demo.repository.ScheduleRepository;
import com.cosmicchimps.demo.service.ScheduleService;
import com.cosmicchimps.demo.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class ScheduleServiceTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ScheduleController scheduleController;
    @Autowired
    private ScheduleService scheduleService;
    @MockBean
    private ScheduleRepository scheduleMockRepository;

    @Test
    void contextLoads() {
        assertThat(scheduleController).isNotNull();
    }

    @Test
    public void scheduleShouldCreate() throws Exception {
        NewSchedule s = new NewSchedule(
                Utils.parseStringToLocalDate("2022-05-24"),
                Utils.parseStringToLocalDate("2022-05-25"),
                "WEEKLY",
                1,
                Arrays.asList("SU"));
        when(scheduleMockRepository.save(any(Schedule.class))).thenReturn(new Schedule());
        String ss = mapper.writeValueAsString(s);
        mvc
                .perform(post("/schedule/").content(ss)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").isEmpty());
    }

    @Test
    public void scheduleShouldNotCreate() throws Exception {
        NewSchedule s = new NewSchedule(
                Utils.parseStringToLocalDate("2022-05-25"),
                Utils.parseStringToLocalDate("2022-05-24"),
                "WEEKLY",
                1,
                Arrays.asList("SU"));
        when(scheduleMockRepository.save(any(Schedule.class))).thenReturn(new Schedule());
        String ss = mapper.writeValueAsString(s);
        mvc
                .perform(post("/schedule/").content(ss)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").doesNotExist());
    }

    @Test
    public void scheduleShouldBeValid() throws Exception {
        Schedule s = Schedule.builder()
                .id("test")
                .startDate(Utils.parseStringToLocalDate("2022-06-01"))
                .endDate(Utils.parseStringToLocalDate("2022-06-08"))
                .frequency("WEEKLY")
                .dayList(Arrays.asList("MO", "TU", "WE", "TH", "FR"))
                .created(LocalDate.now())
                .build();
        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-02"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(true));
    }

    @Test
    public void scheduleShouldNotBeValid() throws Exception {
        Schedule s = Schedule.builder()
                .id("test")
                .startDate(Utils.parseStringToLocalDate("2022-06-01"))
                .endDate(Utils.parseStringToLocalDate("2022-06-08"))
                .frequency("WEEKLY")
                .dayList(Arrays.asList("MO", "TU", "WE", "TH", "FR"))
                .created(LocalDate.now())
                .build();
        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-04"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));
    }


    @Test
    public void scheduleShouldValidateIntervalBy2() throws Exception {
        Schedule s = Schedule.builder()
                .id("test")
                .startDate(Utils.parseStringToLocalDate("2022-06-01"))
                .endDate(Utils.parseStringToLocalDate("2022-06-30"))
                .frequency("WEEKLY")
                .dayList(Arrays.asList("MO", "TU", "WE", "TH", "FR"))
                .interval(2)
                .created(LocalDate.now())
                .build();

        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-01"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(true));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-02"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(true));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-03"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(true));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-04"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-05"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-06"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-07"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-08"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-09"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-11"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-12"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-13"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(true));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-14"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(true));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-15"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(true));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-16"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(true));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-17"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(true));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-18"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-19"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-20"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-21"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-22"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-23"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-24"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-25"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-26"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(false));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-27"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(true));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-28"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(true));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-29"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(true));


        when(scheduleMockRepository.findById(any(String.class))).thenReturn(Optional.of(s));
        mvc
                .perform(get("/schedule/test/is-valid/2022-06-30"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isValid").value(true));
    }

}
