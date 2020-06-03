package com.carl.puzzlegame.web.room.models;

import com.carl.puzzlegame.domain.code.JoinRoomStatus;
import lombok.Value;

import java.util.List;

/**
 * Response model for join room
 */
@Value
public class JoinRoomResponse {
    private JoinRoomStatus status;
    private List<String> playersInRoom;
    private Boolean readyToStart;
}
