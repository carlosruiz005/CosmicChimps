package com.cosmicchimps.demo.service;

import com.cosmicchimps.demo.dto.NewSchedule;
import com.cosmicchimps.demo.entity.Schedule;
import com.cosmicchimps.demo.repository.ScheduleRepository;
import com.cosmicchimps.demo.util.Utils;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
@Service
public class ScheduleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Map<String, String> create(NewSchedule newSchedule) {
        Schedule s = this.modelMapper.map(newSchedule, Schedule.class);
        s.setCreated(LocalDate.now());
        s = this.scheduleRepository.save(s);
        Map<String, String> response = new HashMap();
        response.put("id", s.getId());
        return response;
    }

    public Map<String, Boolean> isValid(String scheduleId, String date) {
        Map<String, Boolean> response = new HashMap();
        Optional<Schedule> op = this.scheduleRepository.findById(scheduleId);
        response.put("isValid",
                !op.isEmpty()
                && Utils.isDateInRange(date, op.get())
                && Utils.isEffectiveDay(date, op.get())
                && Utils.isWeekIntervalEffective(date, op.get()));
        return response;
    }
}
