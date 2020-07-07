package com.orchestra.orchestra.services;

import com.orchestra.orchestra.modals.Admin;
import com.orchestra.orchestra.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean signup(Admin admin) {
        try {
            admin.setAccount_date(new Date().toString());
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            admin.setActive(true);
            adminRepository.save(admin);

            System.out.println("Admin " + admin.getEmail() + " added.");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public Admin update(Admin admin, Principal principal) {
        if(adminRepository.findByEmail(principal.getName()).isPresent()) {
            return adminRepository.save(admin);
        }

        return null;
    }
}
