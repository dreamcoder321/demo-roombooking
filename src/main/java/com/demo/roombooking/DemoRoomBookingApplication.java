package com.demo.roombooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*


[{"room::{"roomId":"abc","roomType":"SINGLE"},{"bookingDates":["2013-07-21T19:32:00Z","2019-07-21T19:32:00Z"]}]


{"room":{"roomId":"abc","roomType":"SINGLE"},"bookingDates":["2013-07-21T19:32:00Z","2019-07-21T19:32:00Z"]}

*/

/*

 {"roomId":"abc","roomName": "room for dreamer","roomType": "SINGLE"}

*/

@SpringBootApplication
public class DemoRoomBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoRoomBookingApplication.class, args);
    }

}
