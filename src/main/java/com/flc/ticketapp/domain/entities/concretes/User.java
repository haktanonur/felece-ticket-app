package com.flc.ticketapp.domain.entities.concretes;

import com.flc.ticketapp.domain.entities.abstracts.BaseEntity;
import com.flc.ticketapp.domain.entities.listeners.UserListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "tb_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "uq_username", name = "uk_user_username"),
        }
)
@EntityListeners(UserListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity<UUID> {

    @Id
    @GeneratedValue
    @Column(name = "id_user", updatable = false, nullable = false, length = 16)
    protected UUID id;

    @Column(name = "uq_username", nullable = false)
    protected String username;

    @Column(name = "tx_password", nullable = false, length = 60)
    protected String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_user_role",
            joinColumns = @JoinColumn(name = "rf_user", nullable = false), foreignKey = @ForeignKey(name = "fk_user_role_user"),
            inverseJoinColumns = @JoinColumn(name = "rf_role", nullable = false), inverseForeignKey = @ForeignKey(name = "fk_user_role_role"),
            uniqueConstraints = @UniqueConstraint(name = "uk_user_role_user_role", columnNames = {"rf_user", "rf_role"})
    )
    protected Set<Role> roles;

    public User(String username) {
        this.username = username;
    }

}

