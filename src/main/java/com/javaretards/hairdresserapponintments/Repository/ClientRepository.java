package com.javaretards.hairdresserapponintments.Repository;

import com.javaretards.hairdresserapponintments.Entity.Client;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author mateusz
 */
public interface ClientRepository extends CrudRepository<Client, Long>{
    Optional<Client> findByPhone(String phone);
}
