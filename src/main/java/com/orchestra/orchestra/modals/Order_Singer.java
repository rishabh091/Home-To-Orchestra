package com.orchestra.orchestra.modals;

import javax.persistence.*;

@Entity
public class Order_Singer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long os_id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Singer singer;

    public long getOs_id() {
        return os_id;
    }

    public void setOs_id(long os_id) {
        this.os_id = os_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }
}
