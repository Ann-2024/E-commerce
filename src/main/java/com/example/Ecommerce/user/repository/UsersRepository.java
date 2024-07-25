package com.example.Ecommerce.user.repository;

import com.example.Ecommerce.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("SELECT s FROM Users s WHERE s.email =?1 ")


    Optional<Users> findByEmail(String email);
}
