package com.flc.ticketapp.repository.abstracts;

import com.flc.ticketapp.domain.entities.concretes.User;
import com.flc.ticketapp.service.model.response.user.UserAuthProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = """
                select s from User s
                left join fetch s.roles r
                where s.username = :username
            """)
    <P> Optional<P> findUserAuthoritiesByUsername(String username, Class<P> projection);

    @Query(value = """
                select s.password from User s
                where s.username = :username
            """)
    String findPasswordByUsername(@Param("username") String username);



}
