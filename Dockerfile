FROM java:openjdk-8u91-jdk
MAINTAINER mozammaltomal.1001@gmail.com
VOLUME /tmp
COPY target/demo-room-booking-0.0.1-SNAPSHOT.jar demo-room-booking.jar
RUN bash -c 'touch /demo-room-booking.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/demo-room-booking.jar"]