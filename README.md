# how to run room booking rest apis 

1. install jdk 8, mvn, docker, docker-compose

2. cd demo-roombooking

3. sudo bash run.sh

Now the rest aps is running at http://http://localhost:8080

you can access swagger at http://localhost:8080/api/swagger-ui.html

4. you need to have jwt token to get access the protected apis. 
In order to generate the jwt bearer token you need to do the following steps: 

jwt toke generator url:
  http://localhost:8080/api/auth
rest method: 
  POST

Body urlencoded with the following username and password:
  
username: admin
password: admin

5. Now you can create room, booking. you can find all rooms, bookings. you can also cancel booking using the above bearer token.
you can find api documentation at http://localhost:8080/api/swagger-ui.html

6. A simple mock testing is provided.

7. This a simple demo without any business related concerns since that require open-ended discussion. 

