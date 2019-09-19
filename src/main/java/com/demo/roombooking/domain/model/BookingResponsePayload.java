package com.demo.roombooking.domain.model;

import com.demo.roombooking.domain.entity.Booking;
import com.demo.roombooking.domain.entity.BookingCalendar;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponsePayload {

  @NotNull private Long bookingId;

  @Valid private RoomDTO room;

  @NotNull private Set<BookingCalendar> bookingDates = new HashSet<>();

  public static BookingResponsePayload createBookingResponsePayload(Booking booking) {
    return BookingResponsePayload.builder()
        .bookingId(booking.getId())
        .room(RoomDTO.createRoomDTO(booking))
        .bookingDates(booking.getBookingCalendars())
        .build();
  }
}
