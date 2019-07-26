package com.carl.tetris.web.players.models;

import lombok.Data;

@Data
public class PlayerJoinRequest {
    private String roomName;

    public PlayerJoinRequest() {}
}
