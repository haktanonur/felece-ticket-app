package com.flc.ticketapp.domain.entities.concretes;

import com.flc.ticketapp.domain.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_vehicle")
public class Vehicle  extends BaseEntity<UUID> {

    @Id
    @GeneratedValue
    @Column(name = "id_user", updatable = false, nullable = false, length = 16)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @Column(name = "qt_capacity", nullable = false)
    private Integer capacity;

    @Column(name = "dt_date_time", nullable = false)
    private LocalDateTime dateTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehicle")
    private Set<Ticket> tickets;

    @Column(name = "qt_price", nullable = false)
    private Double price;

}
