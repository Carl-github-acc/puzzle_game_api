package com.carl.tetris.web.players.models;

import lombok.Value;

import java.util.List;

@Value
public class PlayerJoinResponse {
    private String roomName;
    private String playerToken;
    private List<String> playersInRoom;
    private Boolean readyToStart;
}
