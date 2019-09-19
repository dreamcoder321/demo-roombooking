package com.demo.roombooking.domain.model;

import com.demo.roombooking.domain.entity.Booking;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

  private Long id;

  private String roomId;

  private String roomName;

  private String roomDescription;

  private String roomType;

  public static RoomDTO createRoomDTO(Booking booking) {

    return RoomDTO.builder()
        .id(booking.getRoom().getId())
        .roomId(booking.getRoom().getRoomId())
        .roomName(booking.getRoom().getRoomName())
        .roomDescription(booking.getRoom().getRoomDescription())
        .roomType(booking.getRoom().getRoomType().name())
        .build();
  }
}
