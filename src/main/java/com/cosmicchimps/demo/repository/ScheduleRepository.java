package com.cosmicchimps.demo.repository;

import com.cosmicchimps.demo.entity.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
}
