package com.javaretards.hairdresserapponintments.Repository;

import com.javaretards.hairdresserapponintments.Entity.Appointment;
import com.javaretards.hairdresserapponintments.Entity.Client;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author mateusz
 */
public interface AppointmentRepository extends CrudRepository<Appointment, Object>{
    Optional<Appointment> findTopByClientOrderByIdDesc(Client client);
}
