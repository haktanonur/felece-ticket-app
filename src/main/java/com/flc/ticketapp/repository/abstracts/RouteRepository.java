package com.flc.ticketapp.repository.abstracts;

import com.flc.ticketapp.domain.entities.concretes.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RouteRepository extends JpaRepository<Route, UUID> {

    @Modifying
    @Query("update Route r set r.from = :from where r.id = :id")
    int updateFrom(UUID id, String from);

    @Modifying
    @Query("update Route r set r.to = :to where r.id = :id")
    int updateTo(UUID id, String to);

}
