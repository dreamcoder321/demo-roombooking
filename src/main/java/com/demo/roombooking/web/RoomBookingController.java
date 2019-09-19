package com.demo.roombooking.web;

import com.demo.roombooking.domain.entity.Booking;
import com.demo.roombooking.domain.entity.BookingCalendar;
import com.demo.roombooking.domain.entity.Room;
import com.demo.roombooking.domain.model.BookingRequestPayload;
import com.demo.roombooking.domain.model.BookingResponsePayload;
import com.demo.roombooking.domain.model.RoomAggregateDTO;
import com.demo.roombooking.services.RoomBookingService;
import com.demo.roombooking.web.exception.RoomNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
@Api(value = "Room Booking APIs")
public class RoomBookingController {

  private final RoomBookingService roomBookingService;

  @ApiOperation(value = "find all rooms", notes = "Returns all rooms with calendar")
  @GetMapping("/rooms")
  public ResponseEntity<List<RoomAggregateDTO>> findAllRooms() {
    return ResponseEntity.ok(roomBookingService.findAllRooms());
  }

  @ApiOperation(value = "find a room by roomId", notes = "Returns a room with specific roomId")
  @GetMapping("/rooms/{roomId}")
  public ResponseEntity<Room> findRoomById(@PathVariable String roomId)
      throws RoomNotFoundException {
    Optional<Room> room = roomBookingService.findRoomById(roomId);
    if (!room.isPresent()) {
      log.error("room with id {} is not exists", roomId);
    }
    return ResponseEntity.ok(room.get());
  }

  @ApiOperation(value = "remove a room object by roomId", notes = "remove a roomId")
  @DeleteMapping("/room/{id}")
  public ResponseEntity delete(@PathVariable String id) {
    roomBookingService.deleteByRoomId(id);
    return ResponseEntity.noContent().build();
  }

  @ApiOperation(value = "create room object", notes = "create a room by post method")
  @PostMapping("/room")
  public ResponseEntity<?> createRoom(@Valid @RequestBody Room room) {
    Room createdRoom = roomBookingService.createRoom(room);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdRoom.getRoomId())
            .toUri();
    return ResponseEntity.created(location).build();
  }

  @ApiOperation(value = "create a booking object", notes = "Returns booking object if succeeded")
  @PostMapping("/booking")
  public ResponseEntity<?> createBooking(
      @Valid @RequestBody BookingRequestPayload bookingRequestPayload)
      throws RoomNotFoundException {
    for (LocalDateTime d : bookingRequestPayload.getBookingDates())
      log.info("fuck uuuuu {}", d.toString());
    Booking createdNewBooking = roomBookingService.createBooking(bookingRequestPayload);
    log.info("created booking calendar1121 {}", createdNewBooking.getBookingCalendars().size());
    for (BookingCalendar c : createdNewBooking.getBookingCalendars())
      log.info("created booking calendar {}", c.getBookingDate());
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdNewBooking.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }

  @ApiOperation(value = "return all booking objects", notes = "Returns list of booking objects")
  @GetMapping("/bookings")
  public ResponseEntity<List<BookingResponsePayload>> findAllBooking() {
    List<BookingResponsePayload> bookings = roomBookingService.findAllBooking();
    return ResponseEntity.ok(bookings);
  }

  @ApiOperation(value = "cancel a specific booking by id", notes = "cancel specific booking")
  @DeleteMapping("/bookings/{id}")
  public ResponseEntity cancelBooking(@PathVariable Long id) {
    roomBookingService.cancelBooking(id);
    return ResponseEntity.noContent().build();
  }
}
