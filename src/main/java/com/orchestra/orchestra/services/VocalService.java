package com.orchestra.orchestra.services;

import com.orchestra.orchestra.modals.Singer;
import com.orchestra.orchestra.modals.Vocal;
import com.orchestra.orchestra.modals.Vocal_Singer;
import com.orchestra.orchestra.repo.AdminRepository;
import com.orchestra.orchestra.repo.SingerRepository;
import com.orchestra.orchestra.repo.VocalRepository;
import com.orchestra.orchestra.repo.Vocal_SingerRepository;
import com.orchestra.orchestra.services.helpers.ImageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VocalService {

    @Autowired
    private VocalRepository vocalRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private Vocal_SingerRepository vocalSingerRepository;

    public Vocal addVocal(Vocal vocal, Principal principal) {
        if(adminRepository.findByEmail(principal.getName()).isPresent()) {
            vocal.setDay_created(new Date().toString());
            return vocalRepository.save(vocal);
        }

        return null;
    }

    public List<Vocal> getAll() {
        return vocalRepository.findAll();
    }

    public List<Vocal_Singer> getById(long id) {
        return vocalSingerRepository.findAllByVocal(
                vocalRepository.findById(id).get()
        );
    }

    public Vocal uploadCover(String image, long id, Principal principal) {
        if(adminRepository.findByEmail(principal.getName()).isPresent()) {
            Vocal vocal = vocalRepository.findById(id).orElse(null);

            if(vocal != null) {
                vocal.setCover_pic(ImageHelper.compress(image.getBytes()));
                return vocalRepository.save(vocal);
            }
        }

        return null;
    }

    public Vocal_Singer addSingerToVocal(long vocalId, long singerId, Principal principal) {
        if(adminRepository.findByEmail(principal.getName()).isPresent()) {
            Optional<Vocal> vocalOptional = vocalRepository.findById(vocalId);
            Optional<Singer> singerOptional = singerRepository.findById(singerId);

            if(vocalOptional.isPresent() && singerOptional.isPresent()) {
                Vocal_Singer vocalSinger = new Vocal_Singer();
                vocalSinger.setSinger(singerOptional.get());
                vocalSinger.setVocal(vocalOptional.get());

                return vocalSingerRepository.save(vocalSinger);
            }
        }

        return null;
    }
}
