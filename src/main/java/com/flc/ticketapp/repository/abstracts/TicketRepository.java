package com.flc.ticketapp.repository.abstracts;

import com.flc.ticketapp.domain.entities.concretes.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    @Query(value = """
                select t.passenger.username from Ticket t
                where t.id = :id
            """)
    Optional<String> findUsernameById(UUID id);

    @Modifying
    @Query("update Ticket t set t.status = com.flc.ticketapp.domain.enumeration.TicketStatus.POSTPONED where t.id = :id")
    int postpone(UUID id);

    @Modifying
    @Query("update Ticket t set t.status = com.flc.ticketapp.domain.enumeration.TicketStatus.CANCELED where t.id = :id")
    int cancel(UUID id);

}
