package com.flc.ticketapp.domain.entities.concretes;

import com.flc.ticketapp.domain.entities.listeners.SuperAdminListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "tb_admin")
@EntityListeners(SuperAdminListener.class)
@PrimaryKeyJoinColumn(name = "id_user", referencedColumnName = "id_user", foreignKey = @ForeignKey(name = "fk_admin_user"))
@OnDelete(action = OnDeleteAction.CASCADE)
public class Admin extends User {
}
