package org.askforai.auth;

import java.util.Optional;

import org.askforai.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(@Param("username") String username);
}
