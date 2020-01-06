package com.javaretards.hairdresserapponintments.Repository;

import com.javaretards.hairdresserapponintments.Entity.ServiceOption;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author mateusz
 */
public interface ServiceRepository extends CrudRepository<ServiceOption, Long>{
    List<ServiceOption> findByDeletedFalse();
}
