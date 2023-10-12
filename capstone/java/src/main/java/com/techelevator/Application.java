package com.techelevator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        String account_sid = "AC202a198c3c233ca429ba015a14cd5485";
        String auth_token = "6f58fb0a0dbf22dfcb664dfb0db97009";
        Twilio.init( account_sid,  auth_token);
    }

}
