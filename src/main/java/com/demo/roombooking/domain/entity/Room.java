package com.demo.roombooking.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Room extends BaseEntity {
  @NonNull
  @NotNull
  @NotEmpty
  @Column(nullable = false, unique = true, length = 64)
  private String roomId;

  private String roomName;

  private String roomDescription;

  @JsonIgnore
  @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Booking> bookings = new HashSet<>();

  public void addBooking(Booking booking) {
    bookings.add(booking);
    booking.setRoom(this);
  }

  public void removeBooking(Booking booking) {
    bookings.remove(booking);
    booking.setRoom(null);
  }

  @Enumerated(EnumType.STRING)
  private RoomType roomType;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Room room = (Room) o;
    return Objects.equals(id, room.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
