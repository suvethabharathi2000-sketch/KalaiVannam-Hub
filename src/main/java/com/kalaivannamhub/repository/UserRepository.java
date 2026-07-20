
package com.kalaivannamhub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kalaivannamhub.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
