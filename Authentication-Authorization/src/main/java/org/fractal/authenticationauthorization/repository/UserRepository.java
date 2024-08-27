package org.fractal.authenticationauthorization.repository;
import org.fractal.authenticationauthorization.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
