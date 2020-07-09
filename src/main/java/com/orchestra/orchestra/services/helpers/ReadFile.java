package com.orchestra.orchestra.services.helpers;

import com.orchestra.orchestra.modals.Order;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFile {

    public static String readAcceptedMail(Order order) throws Exception {
        final String acceptedMailFile = "/mails/accepted.txt";

        return readFile(acceptedMailFile)
                .toString()
                .replace("{venue}", order.getAddress() + ", " + order.getCity() + ", " + order.getState())
                .replace("{date}", order.getVenue_date())
                .replace("{time}", order.getTime());
    }

    public static String readRejectMail(Order order) throws Exception {
        final String rejectMailFile = "/mail/reject.txt";

        return readFile(rejectMailFile)
                .toString()
                .replace("{venue}", order.getAddress() + ", " + order.getCity() + ", " + order.getState())
                .replace("{date}", order.getVenue_date())
                .replace("{time}", order.getTime());
    }

    private static StringBuilder readFile(String location) throws Exception{
        InputStream inputStream = ReadFile.class.getResourceAsStream(location);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();

        while (line != null) {
            stringBuilder.append(line);
            line = bufferedReader.readLine();
        }

        return stringBuilder;
    }
}
