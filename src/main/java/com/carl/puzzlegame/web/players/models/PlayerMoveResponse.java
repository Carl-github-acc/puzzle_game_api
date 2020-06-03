package com.carl.puzzlegame.web.players.models;

import com.carl.puzzlegame.web.game.models.ShapeModel;
import lombok.Value;

/**
 * Response model for when a player moves a shape
 */
@Value
public class PlayerMoveResponse {
    private String playerToken;
    private String roomName;
    private ShapeModel requestShape;
    private ShapeModel currentShape;
}
