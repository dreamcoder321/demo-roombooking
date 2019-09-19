package com.demo.roombooking.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
public class Booking extends BaseEntity {
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private Room room;

  @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<BookingCalendar> bookingCalendars = new HashSet<>();

  public void addBookingCalendar(BookingCalendar bookingCalendar) {
    bookingCalendars.add(bookingCalendar);
    bookingCalendar.setBooking(this);
  }

  public void removeBookingCalendar(BookingCalendar bookingCalendar) {
    bookingCalendars.remove(bookingCalendar);
    bookingCalendar.setBooking(null);
  }

  public Set<BookingCalendar> createBookingCalendarFromBookingDates(
      List<LocalDateTime> bookingDates) {

    for (LocalDateTime bookingDate : bookingDates) {
      BookingCalendar bookingCalendar = new BookingCalendar(bookingDate);
      addBookingCalendar(bookingCalendar);
    }
    return bookingCalendars;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Booking booking = (Booking) o;
    return Objects.equals(id, booking.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
