package com.orchestra.orchestra.services;

import com.orchestra.orchestra.modals.Singer;
import com.orchestra.orchestra.repo.AdminRepository;
import com.orchestra.orchestra.repo.SingerRepository;
import com.orchestra.orchestra.services.helpers.ImageHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SingerService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SingerRepository singerRepository;

    public Singer addSinger(Singer singer, Principal principal) {
        try {
            if(!adminRepository.findByEmail(principal.getName()).isPresent()) {
                return null;
            }

            singer.setDate_added(new Date().toString());
            return singerRepository.save(singer);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Singer> getSingers(Principal principal) {
        return singerRepository.findAll();
    }

    public Singer getSinger(long id, Principal principal) {
        Optional<Singer> singerOptional = singerRepository.findById(id);

        if(singerOptional.isPresent()) {
            Singer singer = singerOptional.get();
            singer
                    .setImage(ImageHelper
                            .decompressBytes(singer.getImage()));

            return singer;
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

    public String uploadImage(String data, long id, Principal principal) {
        if(adminRepository.findByEmail(principal.getName()).isPresent()) {
            Optional<Singer> singerOptional = singerRepository.findById(id);
            if(!singerOptional.isPresent()) {
                return "\"No singer with this id present\"";
            }

            Singer singer = singerOptional.get();
            singer.setImage(ImageHelper.compress(data.getBytes()));
            singerRepository.save(singer);

            return "\"Image Uploaded Successfully\"";
        }

        return "\"Access Denied\"";
    }
}
