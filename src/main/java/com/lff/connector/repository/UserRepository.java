package com.lff.connector.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lff.connector.domain.UserDomain;
public interface UserRepository extends JpaRepository<UserDomain, Long> {

    List<UserDomain> findByEmail(String email);

    List<UserDomain> findByActive(Boolean active);

    List<UserDomain> findByFirstName(String first_name);

    List<UserDomain> findByLastName(String last_name);

    UserDomain findByLogin(String login);

}
