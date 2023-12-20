package com.nish.nishDemo.secondary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nish.nishDemo.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
