package com.backendjavatown.services;


import com.backendjavatown.models.Cd;
import com.backendjavatown.persistence.CdRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final CdRepository cdRepository;

    public EmployeeService(CdRepository cdRepository) {
        this.cdRepository = cdRepository;
    }


    public void create(Cd cd) {
        cdRepository.save(cd);
    }

}
