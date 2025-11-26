package com.example.demo.repository;

import com.example.demo.model.UserInteractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInteractionRepository extends JpaRepository<UserInteractionEntity, String> {
    List<UserInteractionEntity> findByUserId(String userId);
}
