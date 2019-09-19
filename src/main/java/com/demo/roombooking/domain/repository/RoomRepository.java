package com.demo.roombooking.domain.repository;

import com.demo.roombooking.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
  Optional<Room> findByRoomId(String roomId);

  void deleteByRoomId(String roomId);
}
