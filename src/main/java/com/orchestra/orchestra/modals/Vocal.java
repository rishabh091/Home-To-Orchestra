package com.orchestra.orchestra.modals;

import javax.persistence.*;

@Entity
public class Vocal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vocal_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String day_created;

    @Column(columnDefinition = "LONGBLOB", nullable = true, length = 3000)
    private byte[] cover_pic;

    public long getVocal_id() {
        return vocal_id;
    }

    public void setVocal_id(long vocal_id) {
        this.vocal_id = vocal_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay_created() {
        return day_created;
    }

    public void setDay_created(String day_created) {
        this.day_created = day_created;
    }

    public byte[] getCover_pic() {
        return cover_pic;
    }

    public void setCover_pic(byte[] cover_pic) {
        this.cover_pic = cover_pic;
    }
}
