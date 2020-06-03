package com.carl.puzzlegame.web.players.models;

import lombok.Value;

import java.util.List;

/**
 * Response model for when a player joins a room
 */
@Value
public class PlayerJoinResponse {
    private String roomName;
    private String status;
    private String playerToken;
    private List<String> playersInRoom;
    private Boolean readyToStart;
}
