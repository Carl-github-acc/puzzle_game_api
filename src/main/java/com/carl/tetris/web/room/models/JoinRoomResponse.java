package com.carl.tetris.web.room.models;

import com.carl.tetris.domain.code.JoinRoomStatus;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
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
