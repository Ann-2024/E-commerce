package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Categories.Categories;
import com.example.Ecommerce.Model.Notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories(basePackages = "com.example.Ecommerce.repository.jpa")
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT s FROM Notification s WHERE s.id =?1 ")
    Optional<Notification> findById(String id);
}
