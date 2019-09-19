package com.demo.roombooking.domain.model;

import com.demo.roombooking.domain.entity.Booking;
import com.demo.roombooking.domain.entity.BookingCalendar;
import com.demo.roombooking.domain.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomAggregateDTO {

  private RoomDTO room;

  private List<LocalDateTime> bookingDates = new ArrayList<>();

  public List<RoomAggregateDTO> createRoomAggregateDTO(List<Room> rooms) {
    List<RoomAggregateDTO> roomAggregateDTOs = new ArrayList<>();
    for (Room room : rooms) {
      RoomDTO roomDTO =
          RoomDTO.builder()
              .roomId(room.getRoomId())
              .roomName(room.getRoomName())
              .roomDescription(room.getRoomDescription())
              .build();
      Set<Booking> bookings = room.getBookings();
      List<LocalDateTime> clonedBookingDate = new ArrayList<>();
      for (Booking booking : bookings) {
        for (BookingCalendar bookingCalendar : booking.getBookingCalendars())
          clonedBookingDate.add(bookingCalendar.getBookingDate());
      }

      roomAggregateDTOs.add(
          RoomAggregateDTO.builder().room(roomDTO).bookingDates(clonedBookingDate).build());
    }
    return roomAggregateDTOs;
  }
}
