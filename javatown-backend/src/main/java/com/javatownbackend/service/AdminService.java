package com.javatownbackend.service;

import com.javatownbackend.models.Admin;
import com.javatownbackend.models.Prepose;
import com.javatownbackend.persistence.AdminRepository;
import com.javatownbackend.persistence.PreposeRepository;
import com.javatownbackend.service.execptions.PreposeException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final PreposeRepository preposeRepository;

    public AdminService(AdminRepository adminRepository, PreposeRepository preposeRepository) {
        this.adminRepository = adminRepository;
        this.preposeRepository = preposeRepository;
    }

    @Transactional
    public void createAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Transactional
    public void creerNouveauPrepose(String name, String email, String phoneNumber) throws PreposeException {
        try {
            preposeRepository.save(new Prepose(name, email, phoneNumber));
        } catch (Exception e) {
            throw new PreposeException("Erreur lors de la création du préposé: " + e.getMessage());
        }
    }
}
