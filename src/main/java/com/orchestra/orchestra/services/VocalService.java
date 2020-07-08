package com.orchestra.orchestra.services;

import com.orchestra.orchestra.modals.Vocal;
import com.orchestra.orchestra.repo.AdminRepository;
import com.orchestra.orchestra.repo.VocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class VocalService {

    @Autowired
    private VocalRepository vocalRepository;

    @Autowired
    private AdminRepository adminRepository;

    public Vocal addVocal(Vocal vocal, Principal principal) {
        if(adminRepository.findByEmail(principal.getName()).isPresent()) {
            return vocalRepository.save(vocal);
        }

        return null;
    }
}
