package com.flc.ticketapp.domain.entities.abstracts;

import com.flc.ticketapp.domain.entities.listeners.CreateAuditableListener;
import com.flc.ticketapp.domain.entities.listeners.UpdateAuditableListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
@EntityListeners({CreateAuditableListener.class, UpdateAuditableListener.class})
public abstract class BaseEntity<ID> implements Entity, Identifiable<ID>, CreateAuditable, UpdateAuditable {

    @Column(name = "dt_created", nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @Column(name = "dt_modified", insertable = false)
    protected LocalDateTime modifiedAt;

    @Column(name = "tx_creator", nullable = false, updatable = false)
    protected String creator;

    @Column(name = "tx_modifier", insertable = false)
    protected String modifier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity<?> baseEntity = (BaseEntity<?>) o;
        return getId() != null && Objects.equals(getId(), baseEntity.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
