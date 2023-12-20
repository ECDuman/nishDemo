package com.nish.nishDemo.secondary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nish.nishDemo.model.NishUser;

@Repository
public interface NishUserRepository extends JpaRepository<NishUser, Long> {
    Optional<NishUser> findByUsername(String username);
    Boolean existsByUsername(String username);
}