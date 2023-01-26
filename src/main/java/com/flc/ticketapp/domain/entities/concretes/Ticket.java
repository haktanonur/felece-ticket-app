package com.flc.ticketapp.domain.entities.concretes;

import com.flc.ticketapp.domain.entities.abstracts.BaseEntity;
import com.flc.ticketapp.domain.enumeration.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_ticket")
public class Ticket extends BaseEntity<UUID> {

    @Id
    @GeneratedValue
    @Column(name = "id_user", updatable = false, nullable = false, length = 16)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @Column(name = "qt_seat_no", nullable = false)
    private Integer seatNo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "qt_status", nullable = false)
    private TicketStatus status;

}
