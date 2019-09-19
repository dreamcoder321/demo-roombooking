package com.demo.roombooking.web.exception;

public class RoomNotFoundException extends Exception {
  private String roomId;

  public RoomNotFoundException(String roomId) {
    this.roomId = roomId;
  }

  @Override
  public String getMessage() {
    return "room with " + roomId + " not found";
  }
}
