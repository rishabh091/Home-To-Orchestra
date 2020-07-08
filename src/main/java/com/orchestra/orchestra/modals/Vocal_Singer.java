package com.orchestra.orchestra.modals;

import javax.persistence.*;

@Entity
public class Vocal_Singer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vs_id;

    @ManyToOne
    private Vocal vocal;

    @ManyToOne
    private Singer singer;

    public long getVs_id() {
        return vs_id;
    }

    public void setVs_id(long vs_id) {
        this.vs_id = vs_id;
    }

    public Vocal getVocal() {
        return vocal;
    }

    public void setVocal(Vocal vocal) {
        this.vocal = vocal;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }
}
