package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT s FROM Users s WHERE s.email = :email ")
    Optional<Users> findByEmail(String email);

   boolean existsByEmail(String email);

}
