package com.demo.roombooking.domain.model;

import com.demo.roombooking.domain.entity.BookingCalendar;
import com.demo.roombooking.domain.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequestPayload {

  @Valid private Room room;

  @NotNull private List<LocalDateTime> bookingDates = new ArrayList<>();

  public void addBookingCalendar(BookingCalendar bookingCalendar) {
    bookingDates.add(bookingCalendar.getBookingDate());
  }

  public void addBookingDate(LocalDateTime bookingDate) {
    bookingDates.add(bookingDate);
  }
}
