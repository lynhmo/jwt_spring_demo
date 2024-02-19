package com.example.loginauthjwt.repository;

import com.example.loginauthjwt.models.ERole;
import com.example.loginauthjwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}