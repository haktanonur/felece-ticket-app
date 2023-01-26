package com.flc.ticketapp.service.helper;

import com.flc.ticketapp.domain.entities.abstracts.Identifiable;
import com.flc.ticketapp.domain.helper.BeanHelper;
import jakarta.persistence.EntityManager;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.repository.JpaRepository;


@UtilityClass
public class EntityServiceHelper {

    private final EntityManager entityManager = BeanHelper.getBean(EntityManager.class);

    public <T extends Identifiable<ID>, ID> T saveAndRefresh(JpaRepository<T, ID> repository, T entity) {
        T saved = repository.saveAndFlush(entity);
        entityManager.refresh(saved);
        return saved;
    }

}
