package com.carinsurance.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("Select distinct c From Client c left join fetch c.cars")
    List<Client> findAllClients();
}
