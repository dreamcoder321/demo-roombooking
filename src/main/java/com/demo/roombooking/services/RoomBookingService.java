package com.demo.roombooking.services;

import com.demo.roombooking.domain.entity.Booking;
import com.demo.roombooking.domain.entity.Room;
import com.demo.roombooking.domain.model.BookingRequestPayload;
import com.demo.roombooking.domain.model.BookingResponsePayload;
import com.demo.roombooking.domain.model.RoomAggregateDTO;
import com.demo.roombooking.web.exception.RoomNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RoomBookingService {

  public Room createRoom(Room room);

  public Booking createBooking(BookingRequestPayload bookingRequestPayload)
      throws RoomNotFoundException;

  List<RoomAggregateDTO> findAllRooms();

  Optional<Room> findRoomById(String roomId) throws RoomNotFoundException;

  void deleteByRoomId(String roomId);

  List<BookingResponsePayload> findAllBooking();

  void cancelBooking(Long bookingId);
}
