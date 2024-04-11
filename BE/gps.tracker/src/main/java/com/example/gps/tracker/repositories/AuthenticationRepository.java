package com.example.gps.tracker.repositories;

import com.example.gps.tracker.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String passwordHash);
}
