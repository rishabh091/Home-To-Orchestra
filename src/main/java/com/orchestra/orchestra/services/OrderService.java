package com.orchestra.orchestra.services;

import com.orchestra.orchestra.modals.Order;
import com.orchestra.orchestra.modals.Order_Singer;
import com.orchestra.orchestra.modals.User;
import com.orchestra.orchestra.modals.Vocal;
import com.orchestra.orchestra.repo.*;
import com.orchestra.orchestra.services.helpers.Mail;
import com.orchestra.orchestra.services.helpers.ReadFile;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private Order_SingerRepository orderSingerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private VocalRepository vocalRepository;

    @Autowired
    private AdminRepository adminRepository;

    public boolean place(String json, Principal principal) {
        Optional<User> userOptional = userRepository.findByEmail(principal.getName());
        if(userOptional.isPresent()) {
            JSONObject jsonObject = new JSONObject(json);

            Order order = new Order();
            Vocal vocal = vocalRepository.findById(jsonObject.getLong("vocal_id")).orElse(null);

            if(vocal == null) {
                return false;
            }

            order.setVocal(vocal);
            order.setAddress(jsonObject.getString("address"));
            order.setCity(jsonObject.getString("city"));
            order.setState(jsonObject.getString("state"));
            order.setType(jsonObject.getInt("type"));
            order.setVenue_date(jsonObject.getString("venue_date"));
            order.setTime(jsonObject.getString("time"));
            order.setDate_placed(new Date().toString());
            order.setUser(userOptional.get());

            order.setCompleted(false);
            order = orderRepository.save(order);

            JSONArray jsonArray = jsonObject.getJSONArray("singers");
            for(int i = 0; i < jsonArray.length(); i++) {
                Order_Singer order_singer = new Order_Singer();

                order_singer.setOrder(order);
                order_singer.setSinger(singerRepository
                        .findById(jsonArray.getLong(i)).get());

                orderSingerRepository.save(order_singer);
            }

            return true;
        }

        return false;
    }

    public List<Order> getOrders(Principal principal, boolean completed) {
        Optional<User> userOptional = userRepository.findByEmail(principal.getName());
        if(userOptional.isPresent()) {
            return orderRepository.findAllByUserAndCompleted(userOptional.get(), completed);
        }

        return new ArrayList<>();
    }

    public List<Order_Singer> getOrderSingers(long orderId, Principal principal) {
        Optional<User> userOptional = userRepository.findByEmail(principal.getName());
        if(userOptional.isPresent()) {
            Order order = orderRepository.findById(orderId).orElse(null);

            if(order != null) {
                return orderSingerRepository.findAllByOrder(order);
            }
        }

        return new ArrayList<>();
    }

    @Transactional
    public boolean delete(long orderId, Principal principal) {
        Optional<User> userOptional = userRepository.findByEmail(principal.getName());
        if(userOptional.isPresent()) {

            Order order = orderRepository.findById(orderId).orElse(null);
            if(order == null) {
                return false;
            }

            orderSingerRepository.deleteAllByOrder(order);
            orderRepository.delete(order);

            return true;
        }

        return false;
    }

    public Object accept(long orderId, Principal principal) {
        if(adminRepository.findByEmail(principal.getName()).isPresent()) {
            Optional<Order> orderOptional = orderRepository.findById(orderId);

            if(orderOptional.isPresent() && !orderOptional.get().isCompleted()) {
                Order order = orderOptional.get();
                order.setCompleted(true);

                //send mail that order is accepted
                User user = order.getUser();
                try {
                    String data = ReadFile.readAcceptedMail(order);
                    sendDataToMail(data, user);
                }
                catch (Exception e) {
                    System.out.println("Can't Accept order " + order.getOrder_id());
                    e.printStackTrace();

                    return "\"Order not Accepted\"";
                }

                return orderRepository.save(order);
            }

            return "\"Order already accepted\"";
        }

        return "\"Access Denied\"";
    }

    public Object reject(long orderId, Principal principal) {
        if(adminRepository.findByEmail(principal.getName()).isPresent()) {
            Order order = orderRepository.findById(orderId).orElse(null);
            if(order == null) {
                return "\"Order not found\"";
            }

            //send mail to user that it is rejected
            User user = order.getUser();
            try {
                String data = ReadFile.readRejectMail(order);
                sendDataToMail(data, user);
            }
            catch (Exception e) {
                System.out.println("Cannot send reject mail");
                e.printStackTrace();

                return "\"Error in sending rejection\"";
            }

            orderSingerRepository.deleteAllByOrder(order);
            orderRepository.delete(order);

            return "\"Order deleted\"";
        }

        return "\"Access Denied\"";
    }

    private void sendDataToMail(String data, User user) {
        JSONObject jsonObject = new JSONObject(data);

        Mail mail = new Mail();
        mail.setUser_email(user.getEmail());
        mail.setSubject(jsonObject.getString("subject"));
        mail.setContent(jsonObject.getString("content"));

        mail.start();
    }
}
