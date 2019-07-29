package com.carl.tetris.web.room.models;

import com.carl.tetris.domain.code.JoinRoomStatus;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class RoomStatusResponse {
    private String status;
    private List<String> playersInRoom;
    private Boolean readyToStart;
}
