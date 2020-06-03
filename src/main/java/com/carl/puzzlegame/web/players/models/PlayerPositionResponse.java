package com.carl.puzzlegame.web.players.models;

import com.carl.puzzlegame.web.game.models.ShapeModel;
import lombok.Value;

/**
 * Response model for currently controlled shape position of the player
 */
@Value
public class PlayerPositionResponse {
    private String playerToken;
    private String roomName;
    private ShapeModel shape;
}
