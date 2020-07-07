package com.orchestra.orchestra.modals;

import javax.persistence.*;

@Entity
public class Singer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long singer_id;

    @Column(nullable = false)
    private String first_name;

    private String last_name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String mobile;

    private String date_added;

    public long getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(long singer_id) {
        this.singer_id = singer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    @Override
    public String toString() {
        return "{singer_id:" + singer_id +
                ", first_name:'" + first_name + '\'' +
                ", last_name:'" + last_name + '\'' +
                ", email:'" + email + '\'' +
                ", mobile:'" + mobile + '\'' +
                ", date_added:'" + date_added + '\'' +
                '}';
    }
}
