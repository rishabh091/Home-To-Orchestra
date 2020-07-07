package com.orchestra.orchestra.services;

import com.orchestra.orchestra.modals.Singer;
import com.orchestra.orchestra.modals.SingingOption;
import com.orchestra.orchestra.repo.AdminRepository;
import com.orchestra.orchestra.repo.OptionRepository;
import com.orchestra.orchestra.repo.SingerRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private OptionRepository optionRepository;

    public String addSinger(String json, Principal principal) {
        try {
            if(!adminRepository.findByEmail(principal.getName()).isPresent()) {
                return "\"Access Denied\"";
            }

            Singer singer = new Singer();
            JSONObject jsonObject = new JSONObject(json);

            singer.setDate_added(new Date().toString());
            singer.setFirst_name(jsonObject.getString("first_name"));
            singer.setLast_name(jsonObject.getString("last_name"));
            singer.setEmail(jsonObject.getString("email"));
            singer.setMobile(jsonObject.getString("mobile"));
            //save singer
            singer = singerRepository.save(singer);
            //saving qualities
            JSONArray jsonArray = jsonObject.getJSONArray("singingOptionList");
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject options = (JSONObject) jsonArray.get(i);

                SingingOption singingOption = new SingingOption();
                singingOption.setLevel(options.getInt("level"));
                singingOption.setStyle(options.getString("style"));
                singingOption.setSinger(singer);
                //save option
                optionRepository.save(singingOption);
            }

            return "\"Singer Added\"";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "\"Singer Already in database\"";
        }
    }

    public String getSingers(Principal principal) {
        if(adminRepository.findByEmail(principal.getName()).isPresent()) {
            JSONArray jsonArray = new JSONArray();

            List<Singer> singerList = singerRepository.findAll();
            for(Singer s: singerList) {
                JSONObject jsonObject = new JSONObject(s.toString());
                List<SingingOption> optionList = optionRepository.findAllBySinger(s);

                jsonObject.append("qualities", optionList);
                jsonArray.put(jsonObject);
            }

            return jsonArray.toString();
        }

        return "\"Access Denied\"";
    }

    public List<SingingOption> getSinger(long id, Principal principal) {
        if(adminRepository.findByEmail(principal.getName()).isPresent()) {
            Singer singer = singerRepository.findById(id).orElse(null);
            if(singer != null) {
                return optionRepository.findAllBySinger(singer);
            }
        }

        return new ArrayList<>();
    }
}
