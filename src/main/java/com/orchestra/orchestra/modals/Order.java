package com.orchestra.orchestra.modals;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_id;

    @ManyToOne
    private Vocal vocal;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private int type;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String date_placed;

    @Column(nullable = false)
    private String venue_date;

    @Column(nullable = false)
    private String time;

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public Vocal getVocal() {
        return vocal;
    }

    public void setVocal(Vocal vocal) {
        this.vocal = vocal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate_placed() {
        return date_placed;
    }

    public void setDate_placed(String date_placed) {
        this.date_placed = date_placed;
    }

    public String getVenue_date() {
        return venue_date;
    }

    public void setVenue_date(String venue_date) {
        this.venue_date = venue_date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
