package com.carl.puzzlegame.web.players.models;

import lombok.Data;

/**
 * Model used for when a player requests to join a room
 */
@Data
public class PlayerJoinRequest {
    private String roomName;

    public PlayerJoinRequest() {}
}
