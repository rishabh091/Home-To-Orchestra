package com.orchestra.orchestra.services;

import com.orchestra.orchestra.modals.Singer;
import com.orchestra.orchestra.modals.SingingOption;
import com.orchestra.orchestra.repo.OptionRepository;
import com.orchestra.orchestra.repo.SingerRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private OptionRepository optionRepository;

    public boolean addSinger(String json) {
        try {
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

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Singer> getSingers() {
        return singerRepository.findAll();
    }

    public Singer getSinger(long id) {
        return singerRepository.findById(id).orElse(null);
    }
}
