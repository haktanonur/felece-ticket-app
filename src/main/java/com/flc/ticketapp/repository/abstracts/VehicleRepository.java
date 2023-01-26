package com.flc.ticketapp.repository.abstracts;

import com.flc.ticketapp.domain.entities.concretes.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    @Modifying
    @Query("update Vehicle v set v.capacity = :capacity where v.id = :id")
    int updateCapacity(UUID id, Integer capacity);

    @Modifying
    @Query("update Vehicle v set v.dateTime = :dateTime where v.id = :id")
    int updateDateTime(UUID id, LocalDateTime dateTime);

    @Modifying
    @Query("update Vehicle v set v.price = :price where v.id = :id")
    int updatePrice(UUID id, Double price);


}
