package com.orchestra.orchestra.services;

import com.orchestra.orchestra.modals.Singer;
import com.orchestra.orchestra.repo.AdminRepository;
import com.orchestra.orchestra.repo.SingerRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SingerService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SingerRepository singerRepository;

    public String addSinger(Singer singer, Principal principal) {
        try {
            if(!adminRepository.findByEmail(principal.getName()).isPresent()) {
                return "\"Access Denied\"";
            }

            singer.setDate_added(new Date().toString());
            singerRepository.save(singer);

            return "\"Singer Added\"";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "\"Singer Already in database\"";
        }
    }

    public List<Singer> getSingers(Principal principal) {
        if(adminRepository.findByEmail(principal.getName()).isPresent()) {
            return singerRepository.findAll();
        }

        return null;
    }

    public Singer getSinger(long id, Principal principal) {
        if(adminRepository.findByEmail(principal.getName()).isPresent()) {
            return singerRepository.findById(id).orElse(null);
        }

        return null;
    }

    public String updateSinger(Singer singer, Principal principal) {
        try {
            if(!adminRepository.findByEmail(principal.getName()).isPresent()) {
                return "\"Access Denied\"";
            }

            singerRepository.save(singer);

            return "\"Singer Updated\"";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "\"Server Error\"";
        }
    }

    @Transactional
    public String delete(long id, Principal principal) {
        if(adminRepository.findByEmail(principal.getName()).isPresent()) {
            Singer singer = singerRepository.findById(id).orElse(null);

            if(singer != null) {
                singerRepository.deleteById(id);
                return "\"Singer Deleted\"";
            }

            return "\"Singer not found\"";
        }

        return "\"Access Denied\"";
    }
}
