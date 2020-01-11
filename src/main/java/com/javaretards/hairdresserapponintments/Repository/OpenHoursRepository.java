package com.javaretards.hairdresserapponintments.Repository;

import com.javaretards.hairdresserapponintments.Entity.OpenHours;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author mateusz
 */
public interface OpenHoursRepository extends CrudRepository<OpenHours, Long>{
    Optional<OpenHours> findByAppliesFrom(LocalDate date);
    Optional<OpenHours> findFirstByAppliesFromBeforeOrderByAppliesFromDesc(LocalDate date);
    Iterable<OpenHours> findAllByOrderByAppliesFromAsc();
}
