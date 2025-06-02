package com.javatownbackend.persistence;

import com.javatownbackend.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long > {

}
