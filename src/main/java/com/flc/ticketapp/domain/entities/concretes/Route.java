package com.flc.ticketapp.domain.entities.concretes;

import com.flc.ticketapp.domain.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_route")
public class Route  extends BaseEntity<UUID> {

    @Id
    @GeneratedValue
    @Column(name = "id_user", updatable = false, nullable = false, length = 16)
    private UUID id;

    @Column(name = "tx_from", nullable = false)
    private String from;

    @Column(name = "tx_to", nullable = false)
    private String to;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "route")
    private Set<Vehicle> vehicles;

}
