# how to run room booking rest apis 
# tested on windows 10 and ubuntu 18

1. install jdk 8, mvn, docker, docker-compose

2. git clone https://github.com/dreamcoder321/demo-roombooking.git

3. cd demo-roombooking

4. sudo bash run.sh

Now the rest aps is running at http://http://localhost:8080

you can access swagger at http://localhost:8080/api/swagger-ui.html

5. you need to have jwt token to get access the protected apis. 
In order to generate the jwt bearer token you need to do the following steps: 

jwt toke generator url:
  http://localhost:8080/api/auth
rest method: 
  POST

Body urlencoded with the following username and password:
  
username: admin
password: admin

6. Now you can create room, booking. you can find all rooms, bookings. you can also cancel booking using the above bearer token.
you can find api documentation at http://localhost:8080/api/swagger-ui.html

Sample JSON payload for creating new Room:

   {"roomId":"abc","roomName": "room for dreamer","roomType": "SINGLE"}

Sample JSON payload for creating new Booking:

   {"room":{"roomId":"abc","roomType":"SINGLE"},"bookingDates":["2013-07-21T19:32:00Z","2019-07-21T19:32:00Z"]}

A few api endpoints:
  http://localhost:8080/api/auth          [method post]
  http://localhost:8080/api/v1/room       [method post]
  http://localhost:8080/api/v1/rooms      [method get]  
  http://localhost:8080/api/v1/booking    [method post]
  http://localhost:8080/api/v1/bookings   [method get]

How to use endpoints:

Sample HTTP post request for generating jwt token:

curl -d "username=admin&password=admin" -X POST http://localhost:8080/api/auth -v

Sample HTTP POST request for creating room:

curl -H "Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJpc3MiOiJyb29tLWJvb2tpbmctYXBpcyIsImF1ZCI6InJvb20tYm9va2luZy1hcHBzIiwic3ViIjoiYWRtaW4iLCJleHAiOjE1Njk4MjU3NDgsInJvbGUiOlsiUk9MRV9VU0VSIl19.mOSStHlErQJ3L_6xDrbwCxfOshiTWaQ0IdxPErZfSoGMwroooTECkj-ApLdW-vhTkD6ruloX3DFiB-n3MBxvMQ" -d '{"roomId":"test2","roomName": "room for test2","roomType": "SINGLE"}' -H "Content-Type: application/json" -X  POST http://localhost:8080/api/v1/room -v

Sample HTTP POST request for creating booking:

curl -H "Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJpc3MiOiJyb29tLWJvb2tpbmctYXBpcyIsImF1ZCI6InJvb20tYm9va2luZy1hcHBzIiwic3ViIjoiYWRtaW4iLCJleHAiOjE1Njk4MjU3NDgsInJvbGUiOlsiUk9MRV9VU0VSIl19.mOSStHlErQJ3L_6xDrbwCxfOshiTWaQ0IdxPErZfSoGMwroooTECkj-ApLdW-vhTkD6ruloX3DFiB-n3MBxvMQ" -d '{"room":{"roomId":"test1","roomType":"SINGLE"},"bookingDates":["2013-07-21T19:32:00Z","2019-07-21T19:32:00Z"]}' -H "Content-Type: application/json" -X  POST http://localhost:8080/api/v1/booking -v

Sample HTTP GET request for finding all rooms:

curl -k -L -H "Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJpc3MiOiJyb29tLWJvb2tpbmctYXBpcyIsImF1ZCI6InJvb20tYm9va2luZy1hcHBzIiwic3ViIjoiYWRtaW4iLCJleHAiOjE1Njk4MjU3NDgsInJvbGUiOlsiUk9MRV9VU0VSIl19.mOSStHlErQJ3L_6xDrbwCxfOshiTWaQ0IdxPErZfSoGMwroooTECkj-ApLdW-vhTkD6ruloX3DFiB-n3MBxvMQ"  -X GET  http://localhost:8080/api/v1/rooms

Sample HTTP GET request for finding all booking:

curl -k -L -H "Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJpc3MiOiJyb29tLWJvb2tpbmctYXBpcyIsImF1ZCI6InJvb20tYm9va2luZy1hcHBzIiwic3ViIjoiYWRtaW4iLCJleHAiOjE1Njk4MjU3NDgsInJvbGUiOlsiUk9MRV9VU0VSIl19.mOSStHlErQJ3L_6xDrbwCxfOshiTWaQ0IdxPErZfSoGMwroooTECkj-ApLdW-vhTkD6ruloX3DFiB-n3MBxvMQ"  -X GET  http://localhost:8080/api/v1/bookings

7. A simple mock testing is provided.

8. This a simple demo without any business related concerns since that warrant open-ended discussion. 

