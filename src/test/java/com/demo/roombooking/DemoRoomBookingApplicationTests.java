package com.demo.roombooking;

import com.demo.roombooking.domain.entity.Room;
import com.demo.roombooking.domain.entity.RoomType;
import com.demo.roombooking.domain.model.RoomAggregateDTO;
import com.demo.roombooking.domain.model.RoomDTO;
import com.demo.roombooking.services.RoomBookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest
public class DemoRoomBookingApplicationTests {

  @Autowired private MockMvc mockMvc;

  @MockBean private RoomBookingService roomBookingService;
  private DateTimeFormatter formatter;

  @Before
  public void setUpTest() {}

  @Test
  @WithMockUser
  public void testShouldFindAllRooms() throws Exception {
    RoomAggregateDTO roomAggregateDTO = new RoomAggregateDTO();
    Room room = new Room();
    room.setId(1L);
    room.setRoomId("abc");
    room.setRoomType(RoomType.SINGLE);
    roomAggregateDTO.setRoom(
        RoomDTO.builder()
            .id(room.getId())
            .roomId(room.getRoomId())
            .roomType(RoomType.SINGLE.name())
            .build());

    roomAggregateDTO
        .getBookingDates()
        .addAll(
            Arrays.asList(
                LocalDateTime.ofInstant(Instant.parse("2013-07-21T19:32:00Z"), ZoneOffset.UTC),
                LocalDateTime.ofInstant(Instant.parse("2019-07-21T19:32:00Z"), ZoneOffset.UTC)));
    /*  roomAggregateDTO.addBookingDate(
        LocalDateTime.ofInstant(Instant.parse("2013-07-21T19:32:00Z"), ZoneOffset.UTC));
    roomAggregateDTO.addBookingDate(
        LocalDateTime.ofInstant(Instant.parse("2019-07-21T19:32:00Z"), ZoneOffset.UTC));*/
    List<RoomAggregateDTO> bookingRequestPayloads = Arrays.asList(roomAggregateDTO);

    ObjectMapper objectMapper = new ObjectMapper();
    String s = objectMapper.writeValueAsString(bookingRequestPayloads);

    BDDMockito.given(roomBookingService.findAllRooms()).willReturn(bookingRequestPayloads);

    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/v1/rooms"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content()
                .json(
                    "[{\"room\":{\"roomId\":\"abc\",\"roomType\":\"SINGLE\"},\"bookingDates\":[\"2013-07-21T19:32:00\",\"2019-07-21T19:32:00\"]}]"));
  }
}
