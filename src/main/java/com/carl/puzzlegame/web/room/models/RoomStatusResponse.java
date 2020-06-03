package com.carl.puzzlegame.web.room.models;

import lombok.Value;

import java.util.List;

/**
 * Response model for getting the current room status
 */
@Value
public class RoomStatusResponse {
    private String status;
    private List<String> playersInRoom;
    private Boolean readyToStart;
}
