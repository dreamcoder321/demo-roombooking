package com.demo.roombooking.services;

import com.demo.roombooking.domain.entity.Booking;
import com.demo.roombooking.domain.entity.Room;
import com.demo.roombooking.domain.model.BookingRequestPayload;
import com.demo.roombooking.domain.model.BookingResponsePayload;
import com.demo.roombooking.domain.model.RoomAggregateDTO;
import com.demo.roombooking.domain.repository.BookingRepository;
import com.demo.roombooking.domain.repository.RoomRepository;
import com.demo.roombooking.web.exception.RoomNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class RoomBookingServiceImpl implements RoomBookingService {

  private final RoomRepository roomRepository;
  private final BookingRepository bookingRepository;

  @Transactional
  @Override
  public Room createRoom(Room room) {
    return roomRepository.save(room);
  }

  @Transactional
  @Override
  public Booking createBooking(BookingRequestPayload bookingRequestPayload)
      throws RoomNotFoundException {

    List<LocalDateTime> bookingDates = bookingRequestPayload.getBookingDates();
    Room room = bookingRequestPayload.getRoom();
    Optional<Room> persistedRoom = roomRepository.findByRoomId(room.getRoomId());
    if (!persistedRoom.isPresent()) {
      throw new RoomNotFoundException(room.getRoomId());
    }
    Booking booking = new Booking();
    booking.createBookingCalendarFromBookingDates(bookingDates);
    log.info("problem line {}, {}", bookingDates.size(), booking.getBookingCalendars().size());
    booking.setRoom(persistedRoom.get());
    return bookingRepository.save(booking);
  }

  @Override
  @Transactional(readOnly = true)
  public List<RoomAggregateDTO> findAllRooms() {
    List<Room> rooms = roomRepository.findAll();

    return new RoomAggregateDTO().createRoomAggregateDTO(rooms);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Room> findRoomById(String roomId) throws RoomNotFoundException {
    Optional<Room> room = roomRepository.findByRoomId(roomId);
    if (!room.isPresent()) {
      throw new RoomNotFoundException(roomId);
    }
    return room;
  }

  @Transactional
  @Override
  public void deleteByRoomId(String roomId) {
    roomRepository.deleteByRoomId(roomId);
  }

  @Transactional(readOnly = true)
  @Override
  public List<BookingResponsePayload> findAllBooking() {
    List<Booking> bookings = bookingRepository.findAll();
    List<BookingResponsePayload> bookingDTOAggregate = new ArrayList<>();
    for (Booking booking : bookings) {
      bookingDTOAggregate.add(BookingResponsePayload.createBookingResponsePayload(booking));
    }
    return bookingDTOAggregate;
  }

  @Transactional
  @Override
  public void cancelBooking(Long bookingId) {
    bookingRepository.deleteById(bookingId);
  }
}
