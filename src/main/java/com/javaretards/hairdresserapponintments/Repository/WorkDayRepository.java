package com.javaretards.hairdresserapponintments.Repository;

import com.javaretards.hairdresserapponintments.Entity.WorkDay;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author mateusz
 */
public interface WorkDayRepository extends CrudRepository<WorkDay, Long>{
    Optional<WorkDay> findByDate(LocalDate date);
}
