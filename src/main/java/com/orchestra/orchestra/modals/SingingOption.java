package com.orchestra.orchestra.modals;

import javax.persistence.*;

@Entity
public class SingingOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long option_id;

    private String style;
    private int level;

    @ManyToOne
    private Singer singer;

    public long getOption_id() {
        return option_id;
    }

    public void setOption_id(long option_id) {
        this.option_id = option_id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }
}
