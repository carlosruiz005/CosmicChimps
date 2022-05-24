package com.cosmicchimps.demo.repository;

import com.cosmicchimps.demo.entity.Schedule;
import java.time.LocalDate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    public Schedule findByIdAndEndDateAfter(String id, LocalDate date);
}
