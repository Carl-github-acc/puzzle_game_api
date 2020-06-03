package com.carl.puzzlegame.web.room;

import com.carl.puzzlegame.web.room.models.RoomStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Controls aspects concerning the room
 */
@RestController
@RequestMapping("room")
public class RoomController {

    private RoomService roomService;

    public RoomController (@Autowired RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Get the current room status
     * @param roomName - room name
     * @return The room status
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RoomStatusResponse roomStatus(@RequestParam("roomName") String roomName) {
        return roomService.getRoomStatus(roomName);
    }
}
