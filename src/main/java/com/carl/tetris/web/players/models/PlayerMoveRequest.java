package com.carl.tetris.web.players.models;

import com.carl.tetris.web.game.models.ShapeModel;
import lombok.Data;

/**
 * Request model for moving a shape
 */
@Data
public class PlayerMoveRequest {
    private String playerToken;
    private String roomName;
    private ShapeModel shape;

    public PlayerMoveRequest() {}
}
